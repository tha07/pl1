import java.io.FileReader;
import java.util.ArrayList;



public class test {

public static char chrs[] = new char[1000000];
public static ArrayList<Integer> positions = new ArrayList<Integer>();
public static ArrayList<Integer> doomsdayArray = new ArrayList<Integer>();
public static int doomcounter[] = {0};
public static int time = 0;
public static int old_pos_counter = 0;
public static int new_pos_counter = 0;

//diavasma arxeiou apo to http://www.java2s.com/Tutorials/Java/IO_How_to/read/Read_char_array_from_a_file.htm
public static void main(String[] argv) throws Exception{
		int elements = readfile(argv[0]);
		//System.out.println(chrs);
		int columnsize = firstApproach(elements);
		
		for(int k = elements+columnsize+1;k<elements+2*(columnsize+1);k++){
			chrs[k]='X';
			if(k==elements+2*columnsize+1) 
				chrs[k]='\n';
		}
		System.out.println(chrs);
		/* 
		int rowSize = elements/columnsize;
		System.out.println(chrs);
		
		while(new_pos_counter>old_pos_counter+1){
			for(int i = old_pos_counter;i<new_pos_counter;i++){
				new_pos_counter=expand(new_pos_counter,positions.get(i),columnsize);
				if(i==new_pos_counter-1) old_pos_counter=i;
			}
			if(doomcounter[0]!=1){
				for(int k =0;k<doomcounter[0];k++){
					chrs[doomsdayArray.get(k)] = '*';
				}
				System.out.println(time+1);
				break;
			}
			time++;

			
		}

		

		 if(doomcounter[0]!=0){
			System.out.println("the world is saved");
		} 

		int i = columnsize+1;

		while(i<elements+columnsize){
			System.out.print(chrs[i]);
			i++;
		}  

		System.out.print("\n"); */
   	}

public static int readfile(String filename) throws Exception{
	FileReader fr = new FileReader(filename);

    	int count,elements=0,keep=0;    	

    	do {
      		count = fr.read(chrs);
      		if(keep==0){
      			elements = count;
      		}
      		//System.out.println(count);
      		 // for (int i = 0; i < count; i++){

         // 		System.out.print(chrs[i]);
      		 // }
      		 keep++;
    	} while (count != -1);
    	return elements;
}

public static int firstApproach(int last){
	int i=0;
	
	Boolean firstTime = true;//True
	int columnsize = 0;


	while(i<last){

		if(chrs[i]=='+' || chrs[i]=='-'){
			positions.add(i);
			new_pos_counter = new_pos_counter + 1;
		}
		// else if(chrs[i]==' '){
		// 	// chrs.remove(chrs[-1]);
		// 	// chrs.remove(chrs[-1]);
		// 	break;
		// }
		else if(firstTime && chrs[i]=='\n'){
			columnsize=i;
			firstTime=false;
/////////////////////////////////////////edw ginetai lathos metafora mporei na ginei sto diavasma////////////
			for (int k = 0;k<last+1;k++){
				chrs[i+k+1]=chrs[k];
				if(k<i+1) chrs[k] = 'X';
			}
			//System.out.println(chrs);
			chrs[columnsize]='\n';
		
			for(int k = 0;k<new_pos_counter;k++){
				positions.set(k,positions.get(k)+columnsize+1);
			}
			i=i+columnsize;
		}
	i=i+1;
	}
	return columnsize;
}

public static int expand(int new_pos_counter,int position, int columnsize){
	if(chrs[position+1]=='.'){
		chrs[position+1] = chrs[position];
		new_pos_counter=new_pos_counter+1;
		positions.add(position+1);
	}
	else if(chrs[position+1]!=chrs[position] && chrs[position+1]!='X' && chrs[position+1]!='\n'){
		doomsdayArray.add(position+1);
		doomcounter[0]=doomcounter[0]+1;
	}
	if(chrs[position-1]=='.'){
		chrs[position-1] = chrs[position];
		new_pos_counter=new_pos_counter+1;
		positions.add(position-1);
	}
	else if(chrs[position-1]!=chrs[position] && chrs[position-1]!='X' && chrs[position-1]!='\n'){
		doomsdayArray.add(position-1);
		doomcounter[0]=doomcounter[0]+1;
	}
	if(chrs[position+columnsize+1]=='.'){
		chrs[position+columnsize+1] = chrs[position];
		new_pos_counter=new_pos_counter+1;
		positions.add(position+columnsize+1);
	}
	else if(chrs[position+columnsize+1]!=chrs[position] && chrs[position+columnsize+1]!='X' && chrs[position+columnsize+1]!='\n'){
		doomsdayArray.add(position+columnsize+1);
		doomcounter[0]=doomcounter[0]+1;
	}

	if(chrs[position-columnsize-1]=='.'){
		chrs[position-columnsize-1] = chrs[position];
		new_pos_counter=new_pos_counter+1;
		positions.add(position-columnsize-1);
	}
	else if(chrs[position-columnsize-1]!=chrs[position] && chrs[position-columnsize-1]!='X' && chrs[position-columnsize-1]!='\n'){
		doomsdayArray.add(position-columnsize-1);
		doomcounter[0]=doomcounter[0]+1;
	}
	return new_pos_counter;
}

}


