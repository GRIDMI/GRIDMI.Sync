# What solution does the library provide?

You can easily synchronize two entities with each other using standard tools. For example: there is a user who creates trips, and the script for creating trips is tied to the user's data. When, for example, a user cancels a trip, you may want to ensure that no other requests are being executed at that moment. That is, to avoid this, the client and the driver canceled the same trip in the same way.

# How it works?

When someone had made request USER = 1 and TRIP = 2,
and at that time the procedure for USER = 1 and TRIP = 2
has not been completed, then the thread will be blocked
until the procedure is completed.

Moreover, if someone makes a request for USER = 1 and TRIP = 3,
then the stream will also be blocked, since USER = 1 and TRIP = 2
may not be completed yet.

If you pass items with a type and ID that are not in the queue,
then they will be executed without waiting or blocking.

# Что предоставляет библиотека?

Вы можете легко синхронизировать две сущности между собой стандартными средствами. Например: есть пользователь, который создает поездки, и сценарий создания поездок завязан на данных пользователя. Когда например, пользователь отменяет поездку, вам может потребоваться гарантия того, что другие запросы в этот момент ни кем не выполняются. То есть, чтобы не было такого, клиент и водитель одинаково отменили одну поездку.

# Как это работает?

Когда кто-то сделал запрос USER = 1 и TRIP = 2,
и в то время процедура для USER = 1 и TRIP = 2
не был завершен, то поток будет заблокирован
пока процедура не будет завершена.

Более того, если кто-то делает запрос для USER = 1 и TRIP = 3,
то поток тоже будет заблокирован, т.к. USER = 1 и TRIP = 2
может быть еще не завершен.

Если вы передаете элементы с типом и идентификатором, которых нет в очереди,
тогда они будут выполнены без ожидания или блокировки.