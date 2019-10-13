# Bank Account Management API
### Open An Account
* [POST] http://localhost:8090/account
* Request body (application/x-www-form-urlencoded)

    | Key    | Description	              | Example	       |
    | ------ | -------------------------- | ---------------|
    | owner  | Name of the account owner  | Arthur Morgan  |
    | amount | Deposit amount             | 500            |

* HTTP response 200
    ```
    {
        "id": 2,
        "creationDate": "2019-10-12T16:37:11.714+0000",
        "type": "BankAccountCreated",
        "content": "{\"owner\":\"Arthur Morgan\",\"amount\":500}",
        "version": 1,
        "aggregate": {
            "id": 1,
            "creationDate": "2019-10-12T16:37:11.626+0000",
            "type": "BankAccount",
            "version": 1
        }
    }
    ```

### Deposit
* [POST] http://localhost:8090/account/{id}/deposit
* Path Variable

    | Key    | Description	              | Example	       |
    | ------ | -------------------------- | ---------------|
    | id     | Id of the bank account     | 1              |

* Request body (application/x-www-form-urlencoded)

    | Key    | Description	              | Example	       |
    | ------ | -------------------------- | ---------------|
    | amount | Deposit amount             | 500            |

* HTTP response 200
    ```
    {
        "id": 3,
        "creationDate": "2019-10-12T16:52:20.193+0000",
        "type": "DepositPerformed",
        "content": "{\"amount\":500}",
        "version": 2,
        "aggregate": {
            "id": 1,
            "creationDate": "2019-10-12T16:52:15.000+0000",
            "type": "BankAccount",
            "version": 2
        }
    }
    ```

### Withdrawal
* [POST] http://localhost:8090/account/{id}/withdrawal
* Path Variable

    | Key    | Description	              | Example	       |
    | ------ | -------------------------- | ---------------|
    | id     | Id of the bank account     | 1              |

* Request body (application/x-www-form-urlencoded)

    | Key    | Description	              | Example	       |
    | ------ | -------------------------- | ---------------|
    | amount | Withdrawal amount          | 200            |

* HTTP response 200
    ```
    {
        "id": 4,
        "creationDate": "2019-10-12T16:56:38.387+0000",
        "type": "WithdrawalPerformed",
        "content": "{\"amount\":200}",
        "version": 3,
        "aggregate": {
            "id": 1,
            "creationDate": "2019-10-12T16:52:15.000+0000",
            "type": "BankAccount",
            "version": 3
        }
    }
    ```

### Check Account Balance
* [GET] http://localhost:8090/account/{id}
* Path Variable

    | Key    | Description	              | Example	       |
    | ------ | -------------------------- | ---------------|
    | id     | Id of the bank account     | 1              |

* HTTP response 200
    ```
    {
        "id": 1,
        "creationDate": "2019-10-12T16:56:41.295+0000",
        "owner": "Arthur Morgan",
        "balance": 800
    }
    ```
