# Telegram-бот, генерирующий QR-код с информацией пользователя

```Spring Boot``` ```Telegram API``` ```kenglxn.qrgen```

# Команды

__```/start```__ - Приветствие

__```/help```__  - Вывод списка доступных комманд

__```/qr```__    - бот дополнительно запрашивает информацию, которую нужно закодировать, и возвращает QR-код в диалог

# Инструкция по запуску

1) Получите token от BotFather на своего бота и проставьте в 
   bot.token в /src/main/resources/application.properties
2) Установите ngrok c этого сайта https://ngrok.com/download (нужен для тоннелей на локальную машину)
3) Выполняем команду в терминале
   
   ```ngrok http 8080```
   
У нас появляется тоннель 
> ![img.png](img/img.png)

4) Копируем ссылку из Forwarding, которая начинается с https БЕЗ СТРЕЛОЧКИ
5) Вставляем ссылку в /src/main/resources в переменную webhook.url

Должно получится так в итоге, но с вашими данными

![img_2.png](img/img_2.png)

## Запускаем бота через Maven

```export BOT_TOKEN=<ваш токен>```

```echo ${BOT_TOKEN}``` - проверка, что есть такая переменная 

```./mvnw install```

```java -jar ./target/bot-0.0.1-SNAPSHOT.jar```

Для запуска через IDE нужно в настройках запуска проставить переменные окружения  

![img.png](img/img12e12e1.png)
![img.png](img/img243а.png)
