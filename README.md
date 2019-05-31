# CSV Reader

API desenvolvida na aula de Sistemas Distribuídos da faculdade Logatti

## Como usar

### Find All

* **GET**
```
Pega todas as informações do banco de dados
```
```
http://localhost:8080/ServiceConsumer/rest/findAll/
```

### Database

* **GET**
```
Pega todas as informações do banco de dados
```
```
http://localhost:8080/ServiceConsumer/rest/database/
```

* **POST**
```
Salva uma 'school' no banco de dados
```
```
http://localhost:8080/ServiceConsumer/rest/database/
```

### Read File

* **GET**
```
Pega as informações de um arquivo CSV e as salva no banco de dados
```
```
http://localhost:8080/ServiceConsumer/rest/readFile/getFile
```