import math
import sys

numSen = int(sys.stdin.readline())

listFreq = [0]*1001

for i in range (numSen):
    Freq = int (sys.stdin.readline())
    listFreq [Freq] += 1

highestFreq = 0
highestFreqList = []

secFreq = 0
secFreqList = []

for i in range (1001):
    if listFreq [i] > highestFreq:
        highestFreq = listFreq [i]
        del highestFreqList [:]
        highestFreqList.append (i)

    elif listFreq [i] == highestFreq:
        highestFreqList.append (i)

for i in range (1001):
    if listFreq [i] > secFreq and listFreq[i] < highestFreq:
        secFreq = listFreq [i]
        del secFreqList [:]
        secFreqList.append (i)
    elif listFreq [i] == secFreq and listFreq[i] < highestFreq:
        secFreqList.append (i)

if len (highestFreqList) > 1:
    highestFreqList.sort(key = int)
    print (abs(highestFreqList[-1] - highestFreqList[0]))
elif len (secFreqList) > 1:
    secFreqList.sort(key = int)
    print (abs(highestFreqList[0] - secFreqList[0])) if (abs(highestFreqList[0] - secFreqList[0])) > (abs(highestFreqList[0] - secFreqList[-1])) else print (abs(highestFreqList[0] - secFreqList[-1]))

else:
    print (abs(highestFreqList [0] - secFreqList[0]))