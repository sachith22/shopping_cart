# shopping cart application

* This is a Maven Project.

# how to run application

1. Open/import application using IDE. ( like Intellij )
2. Go to src/main/resources/application.properties file and change configurations as your local environment. Use MySQL database.
3. Create an empty MySQL database called `shopping_db` or change as you required in application.properties file.
4. Run the application.
5. After starting application, go to http://localhost:8080/swagger-ui/index.html

# implemented features

1. Add a user (with test case)
2. Sign-in user
3. Add a product
4. Add a product to cart
5. Calculate cart

# step to add test shopping cart application

1. Go to swagger url above mentioned. 
2. Create two products(for testing purpose). (return Product code and name after created a product)
3. Create two users (for testing purpose)
4. Sign-in to both users in separately.
5. Go to "Add to cart" in swagger ui for one sign in user, and add a product with 'productCode' and 'quantity'. 
6. Then, Add two products to other user with different quantities.
7. Check both Shopping cart. 

# future development

1. Validate missing validations.
2. Implement category for products.
3. Manage inventory
4. Add payment module
4. Implement address for the users.
5. Implement front-end application for shopping cart.

#end

 

