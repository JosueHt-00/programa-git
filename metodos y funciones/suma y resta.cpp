#include <iostream> 
using namespace std;
 //funcion que se operen dos valores
/*int suma(int num1, int num2);
		int resultado=0;
		resultado = num1+num2;
		return resultado;
}
 
 int resta(int num1, int num2);
		int resultado=0;
		resultado = num1-num2;
		return resultado;
 }*/
 main() { 
 
 void suma(int &num1,int &num2){
 	int resultado =0;
 	num1+=1;
 	num2+=1;
 	resultado = num1+num2;
 	cout<<resultado<<endl;
 }
 
	 main() {
	int a=10,b=20;
	suma(a,b);
	cout<<"a: "<<a<<endl;
	cout<<"b: "<<b<<endl;
	system("pause")

}
