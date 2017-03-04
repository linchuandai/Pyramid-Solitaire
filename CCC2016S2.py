#CCC Senior 2016 Exam Problem 2 Tandem Bicycle

def findSpeed (Dmojistan_list,Pegland_list, number_citizens, question_number):

    speedSum = 0; 
    Dmojistan_list.sort(key=int)

    if question_number == 1:
        Pegland_list.sort(key=int)
    else:
        Pegland_list.sort(key=int,reverse=True)

    for i in range (0, number_citizens):
        if int(Dmojistan_list [i]) > int(Pegland_list [i]):
            speedSum += int (Dmojistan_list [i])
        else:
            speedSum += int (Pegland_list [i])
            
    return speedSum

#inputs from user   
question_number = int (input ())
number_citizens = int (input ())
Dmojistan_citizens = input ()
Pegland_citizens = input ()

Dmojistan_list = Dmojistan_citizens.split (' ')
Pegland_list = Pegland_citizens.split (' ')

print (findSpeed (Dmojistan_list, Pegland_list, number_citizens, question_number))
