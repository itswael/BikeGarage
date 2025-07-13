                                            +-------------------+
                                            |  Firebase Auth     |
                                            | (Google + Email)   |
                                            +---------+---------+
                                                      |
                        +-----------------------------+------------------------------+
                        |                                                            |
              +---------v---------+                                       +----------v----------+
              |  React Web App    |                                       | React Native App     |
              |  (Owner Portal)   |                                       | (Customer Mobile App) |
              +---------+---------+                                       +----------+-----------+
                        |                                                            |
                        +-----------------------------+------------------------------+
                                                      |
                                                      v
                                       +--------------+---------------+
                                       |       Spring Boot API        |
                                       | (RESTful Backend + Services) |
                                       +------+----------------+------+
                                              |                |
                           +------------------+                +------------------+
                           |                                         |
                +----------v----------+                    +---------v----------+
                |     MySQL DB        |                    | Notification Service |
                | (Users, Services,   |                    | (SMS via Twilio or   |
                |  Vehicles, Invoices)|                    |  Fast2SMS)           |
                +----------+----------+                    +----------+-----------+
                           |                                         |
                           |                                 +-------v--------+
                           |                                 | Firebase Cloud |
                           |                                 | Messaging (FCM)|
                           |                                 +----------------+
                           |
         +-----------------v-----------------+
         |          Redis Cache (Optional)   |
         |   (Frequently accessed data)      |
         +----------------+-----------------+
                           |
                 +---------v---------+
                 |    AWS RDS / DB   |   ← or Render DB / ClearDB (for demo)
                 +------------------+

                             
                  [Infrastructure Layer (Optional)]
+--------------------------------------------------------------+
|  AWS Load Balancer (Optional, for production)                |
|  Vercel (React Web)                                          |
|  AWS Elastic Beanstalk or Render (Backend API)               |
+--------------------------------------------------------------+
