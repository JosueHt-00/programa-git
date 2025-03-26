#include<iostream>

using namespace std;

main(){

	int edad=25,*p_edad;
	p_edad=&edad;
	
	cout<<"Edad:"<<edad<<endl;
	cout<<"puntero edad:"<<*p_edad<<endl;
	
	cout<<"------cambiar valores------"<<endl;
	*p_edad = 40;
	
	cout<<"variedad edad:"<<edad<<endl;
	cout<<"puntero edad:"<<*p_edad<<endl;
	
	cout<<"------otra variedad a p_edad valores------"<<endl;
	int edad2=100
	p_edad=&edad2;
	edad=400;
	cout<<"variable Valor:"<<edad<<endl;
	cout<<"puntero Edad:"<<p_edad<<endl;
	cout<<"variedad Edad2:"<<edad2<<endl;
	
	
	

	system("PAUSE");

}
