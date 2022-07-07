def convertIntArrayToPercentArray(arr):
    res = []
    sum = 0
    for i in arr:
        sum += i
    for i in arr:
        res.append(i/sum)

    return res

def ConvertListToDict(lst):
    index = 0
    res_dict = {}
    for i in lst:
        res_dict[index] = i
        index += 1
    return res_dict