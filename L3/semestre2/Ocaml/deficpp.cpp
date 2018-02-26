#include<iostream>
using namespace std;

int rech_ind(int t[], int e){
  for(int i=0 ;i<9 ;i++){
    if(t[i] == e){
      return i;
    }
  }
  return -1;
}


int main() {
  int t[9] = {1,2,3,4,5,6,7,8,9};
  cout << rech_ind(t,6) << endl;
  return 0;
}
