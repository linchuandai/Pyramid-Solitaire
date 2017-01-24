#CCC Senior 2016 Exam Problem 1 Ragaman

#checks if words could be anagram
def check (list1, list2):

    #checks if length of lists are the same
    if len(list1) != len (list2):
        return 'N'

    #while loop to check all characters
    for i in range (0,len (list1)):

        #if the characters are not the same, this checks
        #for any asteriks, and adds one to the index if
        #there is one. If not, then returns 'N'
        if list1[i] != list2[i]:
                if list2[-1] == '*':
                    list2.pop()
                    list2.insert (i, '*')
                else:
                    return 'N'

    return 'A'

#converts strings to a list of character
def stringtolistsort (inputstring):

    inputlist = []
    
    for i in range (0,len(inputstring)):
        inputlist.append(inputstring[i])

    inputlist.sort(reverse=True)
 
    return inputlist

#if file input is used
#opens input file to read input
#with open('s1.1.in') as f:
    content = f.readlines()
    
#converts the separate lines of input into separate strings
#string1 = content.pop(0)
#string2 = content.pop(0)

#if user input is used
#string1 = input ()
#string2 = input ()

#converts the string to a list of characters
list1 = stringtolistsort (string1)
list2 = stringtolistsort (string2)

#Checks and prints result
print (check (list1, list2))
