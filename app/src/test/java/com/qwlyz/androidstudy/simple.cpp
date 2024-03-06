#include <iostream>

void passByReference(int &x) {
    x = 20;
    std::cout << "Inside passByReference function: " << x << std::endl;
}

int main() {
    int num = 10;
    passByReference(num);
    std::cout << "After calling passByReference function: " << num << std::endl;
    return 0;
}
