import sys


filename_f = "doomsday.in1"


Y=[] #edw tha mpei o telikos pinakas
positions=[]
doomsdayArray = []
doomcounter = [0]
#theloume akomh enan pinaka ston opoio tha prosthesoume tis theseis twn stoixeiwn pou prepei na epektathoun





	# print Y
	# print positions
	# print pos_count

def expand(new_pos_counter,position):
	if(Y[position+1]=="."):
		Y[position+1] = Y[position]
		new_pos_counter=new_pos_counter+1
		positions.append(position+1)
	elif(Y[position+1]!=Y[position] and Y[position+1]!="X" and Y[position+1]!="\n"): #and Y[position+1]!="*":
		doomsdayArray.append(position+1)
		doomcounter[0]=doomcounter[0]+1
		#doomsday=True
		Y[position+1]
	if(Y[position-1]=="."):
		Y[position-1] = Y[position]
		new_pos_counter=new_pos_counter+1
		positions.append(position-1)
	elif(Y[position-1]!=Y[position] and Y[position-1]!="X" and Y[position-1]!="\n"): #and Y[position-1]!="*":
		doomsdayArray.append(position-1)
		doomcounter[0]=doomcounter[0]+1

	if(Y[position+columnsize+1]=="."):
		Y[position+columnsize+1] = Y[position]
		new_pos_counter=new_pos_counter+1
		positions.append(position+columnsize+1)
	elif(Y[position+columnsize+1]!=Y[position] and Y[position+columnsize+1]!="X" and Y[position+columnsize+1]!="\n"): #and Y[position+columnsize+1]!="*":
		doomsdayArray.append(position+columnsize+1)
		doomcounter[0]=doomcounter[0]+1

	if(Y[position-columnsize-1]=="."):
		Y[position-columnsize-1] = Y[position]
		new_pos_counter=new_pos_counter+1
		positions.append(position-columnsize-1)
	elif(Y[position-columnsize-1]!=Y[position] and Y[position-columnsize-1]!="X" and Y[position-columnsize-1]!="\n"): #and Y[position-columnsize-1]!="*":
		doomsdayArray.append(position-columnsize-1)
		doomcounter[0]=doomcounter[0]+1
	return new_pos_counter



i=0
time = 0
old_pos_count=0
new_pos_counter=0
myFile = open(filename_f,"r")
firstTime=True


while(True):
    
	Y.append(myFile.read(1))

	if(Y[i]=="+" or Y[i]=="-"):
		positions.append(i)
		new_pos_counter = new_pos_counter + 1
	elif(Y[-1]==""):
		Y.remove(Y[-1])
#		Y.remove(Y[-1])
		break
	elif(firstTime and Y[i]=="\n"):
		columnsize=i
		firstTime=False
		k=0
		for k in range(columnsize+1):
			Y.append(Y[k])
			Y[k] = "X"
		Y[columnsize]='\n'
		k=0
		for k in range(new_pos_counter):
			positions[k]=positions[k]+columnsize+1
		i=i+columnsize
	i=i+1
k=0



columnsize = Y.index('\n')
#Y.append("\n")
for k in range(columnsize):
	#Y[k] = "X"
	Y.append("X")
Y.append("\n")
rowSize = i/columnsize
#print rowSize,columnsize


while(new_pos_counter>old_pos_count+1):
	i=0
	
	for old_pos_count in range(new_pos_counter):
		new_pos_counter = expand(new_pos_counter,positions[old_pos_count])

	if(doomcounter[0]):
	 	i=0
	 	for i in range(doomcounter[0]):
	 		Y[doomsdayArray[i]] = "*"
	 	print time+1

	 	break
	time =time+1

i=columnsize+1

if(not doomcounter[0]):
	print "the world is saved"

while(i<len(Y)-columnsize-1):
 	sys.stdout.write(Y[i])
 	i+=1

