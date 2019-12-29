
## 源码分析基于 Retrofit 2.40版本

## Retrofit 需要的知识点
1.动态代理: 对接口里的方法, 通过反射来调用
2.注解
3.建造者设计模式
4.外观设计模式
5.线程池

## 主要的代码
        ServiceMethod<Object, Object> serviceMethod =
               (ServiceMethod<Object, Object>) loadServiceMethod(method);
        OkHttpCall<Object> okHttpCall = new OkHttpCall<>(serviceMethod, args);
        return serviceMethod.callAdapter.adapt(okHttpCall);

## 标准的网络请求
```
         val retrofit = Retrofit.Builder()
                 .baseUrl("http://wanandroid.com")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build()

         retrofit.create(Service::class.java).list()
                 .enqueue(object : Callback<KnowledgeSystemResponse> {
                     override fun onFailure(call: Call<KnowledgeSystemResponse>, t: Throwable) {
                         LogUtils.d("error: ${t.message}")
                     }

                     override fun onResponse(call: Call<KnowledgeSystemResponse>, response: Response<KnowledgeSystemResponse>) {
                         LogUtils.d("success: $response")
                     }
                 })
```

## Platform
    判断平台的, 并且封装线程切换方法

## Executor

```
 //这个Executor接口有一个execute方法, 他需要你传递一个Runnable对象,会帮你回调到主线程
 static class MainThreadExecutor implements Executor {
      private final Handler handler = new Handler(Looper.getMainLooper());

      @Override public void execute(Runnable r) {
        handler.post(r);
      }
    }
  }
  ```

## builder模式
    Retrofit是入口和ServiceMethod(把接口转换成Http请求的api)都是通过建造者创建出来, 体现高扩展


## Retrofit类

    serviceMethodCache 复用创建过的ServiceMethod



## 接口

### Call
    Call<T> 封装的请求的类型, T 代表返回的Bean对象类型
    execute()
    enqueue(CallBack<T> callback)

### CallBack


## CallAdapter<T,R>接口的设计

```
   T adapt(Call<R> call); T是定义的接口NetworkInterface对象, R是返回的Bean对象类型
   这接口用户可以自定义, 默认Android下是ExecutorCallAdapterFactory类,内部持有call和Execute对象
   CallAdapter默认2个方法, 有个Factory内部抽象类, 抽象方法 get()返回其实例
   CallAdapter用户通过build()Retrofit对象时传递进来,使用一个集合来维护;
   在ServiceMethod类中持有Retrofit对象, build其实例时使用CallAdapter集合匹配一个就返回,并调用adapt方法
    adapt默认返回的是ExecutorCallbackCall,参数是OkHttpCall()对OkHttp3的包装
```

## Converter

    Converter<ResponseBody, R>接口的R是CallAdapter的泛型T


## OkHttpCall<T>

    泛型表示Success的response的类型:
    1.OkHttpCall<T>的T
    2.Call<T>的T
    3.ServiceMethod<R,?>的R
    4.CallAdapter<R,?>的R
    5.Converter<?,R>的R


## 动态代理
   返回一个T, 这是个泛型没有写死, 让用户通过自己定义callAdapter的实现来决定



