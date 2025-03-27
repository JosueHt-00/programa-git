#include<iostream>
using namespace std;
/*struct Estudiante{
	int codigo;
	string nombre;
	int nota;
};
	main(){
	Estudiante estudiante;
	cout<<"Ingrese Codigo: ";
	cin>>estudiante.codigo;
	cin.ignore();
	cout<<"Ingrese Nombre  Completo: ";
	getline(cin,estudiante.nombre);
	cout<<"Ingrese Nota: ";
	cin>>estudiante.nota;
	
	
	cout<<"Codigo: "<<estudiante.codigo<<endl;
	cout<<" Estudiante:"<<estudiante.nombre<<endl;
	cout<<" Nota:"<<estudiante.nota<<endl;
	*/
	
	struct Estudiante{
	int *codigo;
	string *nombre;
	int **notas;
};

 main(){
 	estudiante estudiante;
	int fila=0,columna=0;
	cout<<"Cuantos Estudiantes Desea Agregar: ";
	cin>>fila;	
	
	cout<<"Cuantas Notas Desea Agregar: ";
	cin>>columna;	
	
		estudiante.codigo = new  int *[fila];
		estudiante.nombre = new string[fila];
		estudiante.notas = new int[fila];
		for (int i=0;i<fila;i++){
		estudiante.notas[i] = new int[columna]; 	
		}
		cout<<"--------------Ingrese Notas--------------"<<endl;

		for (int i=0;i<fila;i++){
			cout<<"Codigo: ";
			cin>>estudiante.codigo[i];
			cin.ignore();
			cout<<"Nombre completo:";
			getline(cin,estudiante.nombre[i]); 
		for (int ii=0;ii<columna_notas;ii++){
		cout<<"Ingrese Nota ["<<ii<<"]: ";
		cin>>*(*(estudiante.notas+i)+ii);
	}
	cout<<"------------------------------------"<<endl;
}


	cout<<"--------------Mostrar Notas--------------"<<endl;
for (int i=0;i<fila;i++){
	cout<<"Codigo: "<<estudiante.codigo[i]<<" Estudiante:"<<estudiante.nombre[i]<<"."<<endl;
for (int ii=0;ii<columna_notas;ii++){
	cout<<"Nota ["<<ii + 1 <<"]: "
	<<*(*(estudiante.notas+i)+ii);
	cout<<endl;

	}
		cout<<"------------------------------------"<<endl;
}

for (int i=0;i<fila;i++){
	delete [] estudiante.notas[i];
}

delete[] estudiante.codigo;
delete[] estudiante.nombre;
delete [] estudiante.notas;

	system("PAUSE");
	
}
