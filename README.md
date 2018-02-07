# The Pact JVM Consumer Sample Project Based on pact-jvm-consumer-junit_2.11. 

The repo "pact-ec-app" demonstrates an e-commerce application which depends on the "pact-ec-review-service" restful service. The former (as a consumer) requests the latter (as a provider) to provide rating information (such as a 3-star of a 5-star rating) for a product on the e-commerce website with product id and user name.

The repo "pact-ec-app" is the sample project to show how to use the pact-jvm-consumer-junit_2.11. 


# How to Run It
0. Install jdk 1.8+ and gradle 4
1. Git clone the ["pact-consumer"](https://github.com/zonghui-ddd-spring-cloud/pact-consumer) repo.
2. Run command "./gradlew clean test"
3. The pact file target/pacts/ec_app-review_service.json will be generated.

# Please refer to the ["pact-producer"](https://github.com/zonghui-ddd-spring-cloud/pact-producer) for how to implement the corresponding pact provider.


