## Transaction Service

A Service that provide and manages all of the transaction, including top up payment and payment from wallet to merchant.

## How to run service
All the service related is run by command of `docker-compose up` that provided by [Wallet Service](https://github.com/KrisRaya/wallet-service).
Once the service is up, go to [Transaction Service Swagger UI page](http://localhost:8080/transaction/swagger-ui.html) to hit the provided API.

### Note on the API
* `/doTopUp` API is an API to save transaction history when wallet user doing the top up, this API consume by Wallet Service that directly doing the top up wallet function.