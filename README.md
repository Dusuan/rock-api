<div align="center"> 
  
**API** that lets **add, delete, and get products** in a PostgreSQL DB made with the porpouse of facilitating the lookup of products in a real record store trough a mobile application

Because it uses **springboot**, it can be reutilized for any kinds of products, because is moddeled as if you had a database schema that looks like this, so you can add more attributes to the artists table and product type, but in my case it wasn't necessary, just the many to many relationship between those tables and my product table
![image](https://github.com/user-attachments/assets/f8de6f88-08e5-4b42-b345-21a95da2e50c)

Also uses specified **SQL** queries that give you result based on the name of the product that are similar to it so it can be easier to search for them just as showed in the next image.
![image](https://github.com/user-attachments/assets/462ccd90-d5b8-4d1c-b6c3-17760ac317f9)

The API also has already a simple but effective built in **Docker image** so it can run on every computer without having versioning issues so it can be deployed easily. 
![image](https://github.com/user-attachments/assets/c9e0364c-2fee-447e-9ca6-35c7a1efa70e)
</div>
