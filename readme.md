# korbit 과제

- csv 데이터들은 시간순서대로 입력된다고 가정하였습니다.
- 전체 csv 의 라인 수에서 한번에 처리할 batch size를 정하고, 각각 다른 json 파일로 저장하도록 하였습니다. (./json 아래에 저장) 

### 실행

```
./mvnw clean
./mvnw install
 java -jar ./target/candlechart-0.0.1-SNAPSHOT.jar {second 입력}
```
