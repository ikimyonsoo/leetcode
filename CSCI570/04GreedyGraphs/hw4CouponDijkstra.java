//min cost from s to t 

Denote n = the size of V 
We have a couponTotal = 1 
Init adj list: for every e in E, adj[u].add({v, w_e})
Init dist[n][couponTotal + 1] with MAX_VALUE 
dist[0][0] = 0 

Init a PriorityQueue<int[]> pq which is a minHeap based on cost 
pq.add({0, 0, 0}) // node, coupon, cost

while (!pq.isEmpty()) {
    curr = pq.pop
    v = curr[0], couponLeft = curr[1], currCost = curr[2];
    
    if (v == n - 1) return currCost

    for (neighbor : adj[v]) 
        u = neighbor[0], w = neighbor[1], newCost = w + currCost

        if (newCost < dist[u][couponLeft]) 
            dist[u][couponUsed] = newCost
            pq.add({u, couponUsed, newCost})
        end if 
        
        if (couponUsed < couponTotal)
            couponCost = w / 2 + currPost 
            if (couponCost < dist[u][couponUsed + 1])
                dist[u][couponUsed + 1] = couponCost
                pq.add({u, couponUsed + 1, couponCost})
            end if
        end if 
    end for 
end while 
return -1 
