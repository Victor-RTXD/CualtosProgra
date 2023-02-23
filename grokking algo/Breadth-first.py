from collections import deque
graph = {}
graph["you"] = ["alice","claire","bob"]
graph["bob"] = ["anuj","peggy"]
graph["alice"] = ["peggy"]
graph["claire"] = ["thom","jonny"]
graph["anuj"] = []
graph["peggy"] = []
graph["thom"] = []
graph["jonny"] = []

search_queue = deque()
search_queue += graph["you"]

while search_queue: #isn't empy
    person = search_queue.popleft()
    if person_is_seller(person):
        print(person + "is a mango seller")
    