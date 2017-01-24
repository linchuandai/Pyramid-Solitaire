#CCC Senior 2016 Exam Problem 2 Tandem Bicycle

import math

#Function to find lowest speed
def lowestSpeed (Dmojistan_list,Pegland_list, number_citizens):

    speedSum = 0; 
    Dmojistan_list.sort()
    Pegland_list.sort()

    for i in range (0, math.ceil(number_citizens/2)+1):
        if Dmojistan_list [i] > Pegland_list [i]:
            speedSum += int (Dmojistan_list [i])
        else:
            speedSum += int (Pegland_list [i])
             
    return speedSum

#Function to find highest speed
def highestSpeed (Dmojistan_list,Pegland_list, number_citizens):

    speedSum = 0;
    Dmojistan_list.sort()
    Pegland_list.sort(reverse=True)

    for i in range (0, math.ceil(number_citizens/2)+1):
        if Dmojistan_list [i] > Pegland_list [i]:
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

if question_number == 1:
    print (lowestSpeed (Dmojistan_list, Pegland_list, number_citizens))
elif question_number == 2:
    print (highestSpeed (Dmojistan_list, Pegland_list, number_citizens))
