#include<iostream>

using namespace std;

main(){


/*
int edad,*p_edad;
p_edad=&edad;
cout<<"Ingrese Edad:";	
cin>>edad;
	cout<<*p_edad<<endl;
	
	if (*p_edad>=18){
	cout<<"mayor de edad"<<endl;
	}
	else{
	cout<<"menor de edad"<<endl;
	}
	
	system("PAUSE");

*/

	/*
	int total=0,*p_notas;
	p_notas= new int[total];
	char res;

		do{
						
			cout<<"Ingrese Nota["<<total<<": ";	
			cin>>notas[total];	
			total++;
			cout<<"Desea ingresar otro dato (s/n):";	
			cin>>res;
			}while(res=='s'  || res=='S' );
			cout<<"------mostrar notas------"<<endl;
		for (int i=0;i<total;i++){
		cout<<"Nota"<<i<<":"<<*p_notas<<endl;	
		p_notas++;
}
	delete [] p_notas;
	system("PAUSE");
*/

			int fil=0,col=0,**pm_notas;
			cout<<"Ingresar la cantidad estudiantes: ";
			cin>>fil;	
			cout<<"ingresarla cantidad Notas por estudiantes: ";
			cin>>col;	
			
			pm_notas = new  int *[fil];
			for (int i=0;i<fil;i++){
			pm_notas[i] = new int[col]; 	
			}
			cout<<"--------------Ingrese Notas--------------"<<endl;
			for (int i=0;i<fil;i++){
			for (int ii=0;ii<col;ii++){
				cout<<"Estudiante: "<<i<<" ,Nota: "<<ii<<":";
				cin>>*(*(pm_notas+i)+ii);
				}
				cout<<"------------------------------------"<<endl;
			}
		
				system("PAUSE");


}
