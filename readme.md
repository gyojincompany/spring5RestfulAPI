## Spring Legacy Backend Restful API Project Endpoints

| Method | URL                                      | Parameters         |
|--------|------------------------------------------|--------------------|
| GET    | /company/boards/list                     |                    |
| GET    | /company/boards/detail                   | no=1               |
| POST   | /company/boards/insert                   | Board              |
| POST   | /company/boards/update                   | Board              |
| POST   | /company/boards/delete                   | no                 |
| GET    | /company/qnas/list                       |                    |
| GET    | /company/qnas/detail                     | qno=1              |
| POST   | /company/qnas/insertQuestion             | Qna                |
| POST   | /company/qnas/insertAnswer               | Qna                |
| POST   | /company/qnas/update                     | Qna                |
| POST   | /company/qnas/delete                     | Qna                |
| GET    | /company/datarooms/list                  |                    |
| GET    | /company/datarooms/detail                | no=1               |
| POST   | /company/datarooms/upload                | Dataroom           |
| POST   | /company/datarooms/update                | Dataroom           |
| POST   | /company/datarooms/delete                | Dataroom           |
| GET    | /company/products/list                   |                    |
| GET    | /company/products/detail                 | pno=1              |
| POST   | /company/products/insert                 | Product            |
| POST   | /company/products/update                 | Product            |
| POST   | /company/products/delete                 | Product            |
| GET    | /company/members/list                    |                    |
| GET    | /company/members/detail                  | id=alice           |
| GET    | /company/members/mypage                  | id=alice           |
| POST   | /company/members/join                    | Member             |
| POST   | /company/members/confirm                 | id=alice           |
| POST   | /company/members/update                  | Member             |
| POST   | /company/members/login                   | Member             |
| POST   | /company/members/logout                  |                    |
| POST   | /company/email/send                      |                    |