#include <iostream>
using namespace std;
main(){
	
	//and (y) &&
	// pr (o) //
		
	char lapiz,lapicero,cuaderno;
	cout<<"Trae Lapiz (s/n):";
	cin>>lapiz;
	cout<<"Trae lapicero (s/n):";
	cin>>lapicero;
	cout<<"Trae cuaderno (s/n):";
	cin>>cuaderno;

	if ((lapiz=='s' || lapicero=='s' ) && cuaderno=='s'){
			cout<<lapiz<<endl;
		cout<<"ingresa"<<endl;
	}else{
			cout<<"No puede ingresar"<<endl;
		}
	
 	
system("pause");	
}
