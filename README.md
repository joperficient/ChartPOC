# ChartPOC
Chart POC-Android 

## Time Frame Bar Chart Component 

![image](https://user-images.githubusercontent.com/97707884/150565517-b9fd566a-8109-4e45-9f0a-01e334411d7f.png)

![image](https://user-images.githubusercontent.com/97707884/150565591-d37cb55f-7c9d-4589-af9b-76920f884176.png)

![image](https://user-images.githubusercontent.com/97707884/150565752-06b8c178-de2e-49eb-81a8-a92b41f3d0d9.png)


To use the component just include it into the XML as 
![image](https://user-images.githubusercontent.com/97707884/150565919-79815716-c0c7-439c-a0d7-888b883780c9.png)


Then, set chartData by creating a `ChartData` object. It receives a list of `ChartTimeFrame` 

For example (Creating random data for 1 year or 12 months) 
![image](https://user-images.githubusercontent.com/97707884/150566262-b4faf232-985c-4c53-a2a5-117006904d05.png)

Please keep in mind, the Y axis are float values and X values are always Date values (time axis) 

Then, add the time frame to the ChartData 
![image](https://user-images.githubusercontent.com/97707884/150566603-7e22c9be-1529-427a-9441-10c3007e4312.png)

and use the chartData in the View to show the info 
![image](https://user-images.githubusercontent.com/97707884/150566786-4d952079-3f3b-4b1e-8a36-f95846675857.png)







