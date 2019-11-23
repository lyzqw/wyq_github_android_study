## compileOnly, api, debugImplementation, implementation的使用
    1. compileOnly 只是在编译有效, 当前model不会参与打包. 你要确保别的model有在打包依赖它, 否则打包成apk没有代码; 一般主APP依赖一个库, 组件model可以使用此模式;
    2. api: 共享效果, 参与编译和打包. 一个model使用api, 别的model依赖它, 也会享有依赖库里的库
    3. implementation: 参与编译和打包: 独享效果. 只在当前model有效
    4. debugImplementation: 参与编译和打包, 只在debug有效

## 2. manifestPlaceholders字段
    build.gradle 中可以使用manifestPlaceholders字段数组类型, 设置key:value 格式, 相当于定义字段了, 然后在AndroidManifest 直接$使用;方便了数据的统一管理

## 3. 存储目录
    私有目录 data/data/包名/   是不需要权限直接就可以往里写文件的
    SD卡目录 Environment.getExternalStorageDirectory() 需要动态权限, 才能读写操作

## 4. inline 字段
    默认这个字段是内联的意思, 就是你调用带inline方法时, 他的代码是移动到你的方法来一起执行, 避免了调用方法的进栈和出栈的操作

## 5. run和apply
    如果你要是{}lambda表达式没有返回值, 可以使用run , this表示自己 方便设置参数. 否则使用apply方法返回值为自己
    如果lambda里如要传递自己本身使用带it的 如: also, let


## 6. 约束布局
    1.每个控件必须有id
    2.constraintLayout可以手动拖动,灵活
    3.按照比例约束位置和尺寸
    4.margin 负值不其效果
    5.只能显示一页, 超出不会显示, 为此你可能需要scrollView 
        
    位置:
    layout constraintTop toTopOf   第一个top属性表示当前view的位置, 第二个top表示目标view的位置
    
    layout_constraintTop_toTopOf ------- -----期望视图的上边对齐另一个视图的上边。
    layout_constraintTop_toBottomOf--------期望视图的上边对齐另一个视图的底边。
    layout_constraintTop_toLeftOf ------------期望视图的上边对齐另一个视图的左边。
    layout_constraintTop_toRightOf -----------期望视图的上边对齐另一个视图的右边。
    layout_constraintBottom_toTopOf ---------期望视图的下边对齐另一个视图的上边。
    layout_constraintBottom_toBottomOf----- 期望视图的底边对齐另一个视图的底边。
    layout_constraintBottom_toLeftOf---------期望视图的底边对齐另一个视图的左边。
    layout_constraintBottom_toRightOf -------期望视图的底边对齐另一个视图的右边。
    layout_constraintLeft_toTopOf ------------- 期望视图的左边对齐另一个视图的上边。
    layout_constraintLeft_toBottomOf ---------期望视图的左边对齐另一个视图的底边。
    layout_constraintLeft_toLeftOf -------------期望视图的左边对齐另一个视图的左边。
    layout_constraintLeft_toRightOf ------------期望视图的左边对齐另一个视图的右边。
    layout_constraintRight_toTopOf------------ 期望视图的右边对齐另一个视图的上边。
    layout_constraintRight_toBottomOf --------期望视图的右边对齐另一个视图的底边。
    layout_constraintRight_toLeftOf ------------期望视图的右边对齐另一个视图的左边。
    layout_constraintRight_toRightOf -----------期望视图的右边对齐另一个视图的右边。
    
    可见性的位置: goneMargin
    goneMargin主要用于约束的控件可见性**被设置为gone的时候** 会生效的margin值，属性如下：
    layout_goneMarginStart
    layout_goneMarginEnd
    layout_goneMarginLeft
    layout_goneMarginTop
    layout_goneMarginRight
    layout_goneMarginBottom_
    
    文字的文本对齐:
    layout_constraintBaseline_toBaselineOf
    
    居中和偏移:
    app:layout_constraintBottom_toBottomOf="parent"  
    app:layout_constraintLeft_toLeftOf="parent"  
    app:layout_constraintRight_toRightOf="parent”  
    app:layout_constraintTop_toTopOf="parent"
    
    四都设置是居中,  设置left 和 right 就是水平居中, top和bottom 就是垂直居中
    
    layout_constraintHorizontal_bias 水平偏移比例
    
    尺寸约束:
    可以设置 0dp, 配合代替match_parent_;
    在使用layout_marginBottom时必须先约束自己的位置
  
    <android.support.constraint.ConstraintLayout 
        android:layout_width="match_parent"
        android:layout_height="match_parent">
     
        <TextView
            android:id="@+id/TextView1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="10dp"
            android:layout_marginTop="10dp" 
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintTop_toTopOf="parent"/>
     
    </android.support.constraint.ConstraintLayout>
    
    
    链:
    3个TextView 相互链在一起, layout_constraintHorizontal_weight 可以设置 3个TextView的相对位置, 聚在一起或是展示在2边
    
    辅助工具:
    android.support.constraint.Group 属性app:constraint_referenced_ids “设置多个控件的id” , 可以对他们一起控制(隐藏)
    
    
    Guideline: 辅助线, 不会显示到界面上, 但是能帮你定位; 先思考需要定位那些线
    android:orientation 垂直vertical，水平horizontal
    layout_constraintGuide_begin 开始位置
    layout_constraintGuide_end 结束位置
    layout_constraintGuide_percent 距离顶部的百分比(orientation = horizontal时则为距离左边)
