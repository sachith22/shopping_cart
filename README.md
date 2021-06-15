# Shopping Cart Application

* This is a Maven Project. Developed this Rest web service using Spring Boot. 

# How to run an application

1. Open/import application using IDE. ( like Intellij )
2. Go to src/main/resources/application.properties file and change configurations as your local environment. Use MySQL database.
3. Run the application.
4. After starting application, go to http://localhost:8080/swagger-ui/index.html

# Implemented features

1. Add users to database (with test case)
2. Sign-in user with authentication
3. Add products to database
4. Add products to cart
5. Calculate cart total amount

# Step to add test shopping cart application

1. Go to swagger url above mentioned.(In here can see all endpoints)
2. Create two products(for testing purpose). (return Product code and name after created a product)
3. Create two users (for testing purpose)
4. Sign-in to both users in separately.
5. Go to "Add to cart" in swagger ui for one sign in user, and add a product with 'productCode', 'quantity' with generated 'token' when user created. 
6. Then, Add two products to other user shopping cart with different quantities.
7. Check both Shopping cart. 

# Future developments 

1. Validate missing validations.
2. Implement category for products.
3. Manage inventory
4. Add order module
5. Add payment module
6. Implement address(shipping, billing) for the users.
7. Implement front-end application for shopping cart.
.
.
.

#end

 

