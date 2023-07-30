# currencyRateTelegramBot
![Uploading DogGlossary.gif…]()

**CurrencyService Documentation**

The `CurrencyService` class provides a method to retrieve the official currency exchange rate for a given currency in relation to the South Korean Won (KRW). It fetches data from an external API, parses the JSON response, and returns the relevant currency rate information.

**Method: getCurrencyRate**

```java
public static String getCurrencyRate(String message, CurrencyModel model) throws IOException, ParseException
```

**Description:**

This method fetches the official currency exchange rate information from an external API for the specified currency code in relation to KRW (South Korean Won). It populates the provided `CurrencyModel` object with the relevant exchange rate details and returns a formatted string containing the information.

**Parameters:**

- `message` (String): The currency code for which the exchange rate information is requested. The code should be in uppercase letters. For example, "USD" for US Dollar, "EUR" for Euro, etc.

- `model` (CurrencyModel): An instance of the `CurrencyModel` class that will be populated with the retrieved exchange rate details.

**Exceptions:**

- `IOException`: Thrown when there is an error while reading the response from the API URL.

- `ParseException`: Thrown when there is an error while parsing the date information in the API response.

**Return Value:**

A formatted string containing the official rate of KRW to the specified currency, along with the date of the exchange rate and the buying and selling prices.

**Method: getFormatDate**

```java
public static String getFormatDate(CurrencyModel model)
```

**Description:**

This method takes a `CurrencyModel` object and returns the formatted date string in the format "dd MMM yyyy".

**Parameters:**

- `model` (CurrencyModel): An instance of the `CurrencyModel` class containing the date to be formatted.

**Return Value:**

A formatted date string in the format "dd MMM yyyy".

**TelegramBot Documentation**

The `TelegramBot` class is a Telegram bot implementation that responds to user messages and provides currency exchange rate information using the `CurrencyService` class.

**Constructor: TelegramBot**

```java
public TelegramBot(BotConfig botConfig)
```

**Description:**

This constructor creates an instance of the `TelegramBot` class with the provided `BotConfig` object, which contains the bot token required for Telegram API authentication.

**Parameters:**

- `botConfig` (BotConfig): An instance of the `BotConfig` class containing the bot token.

**Method: getBotUsername**

```java
public String getBotUsername()
```

**Description:**

This method returns the bot's username as registered on Telegram.

**Return Value:**

A string representing the bot's username.

**Method: onUpdateReceived**

```java
public void onUpdateReceived(Update update)
```

**Description:**

This method is called whenever the bot receives an update (message or other events) from Telegram. It processes the user's messages and responds accordingly.

**Parameters:**

- `update` (Update): The update object containing information about the received message.

**Method: startCommandReceived**

```java
private void startCommandReceived(Long chatId, String name)
```

**Description:**

This private method is called when the user sends the "/start" command to the bot. It sends a greeting message to the user with their name.

**Parameters:**

- `chatId` (Long): The unique identifier of the chat where the message was received.

- `name` (String): The first name of the user to whom the greeting message will be sent.

**Method: sendMessage**

```java
private void sendMessage(Long chatId, String textToSend)
```

**Description:**

This private method sends a text message to the specified chat.

**Parameters:**

- `chatId` (Long): The unique identifier of the chat where the message will be sent.

- `textToSend` (String): The text message to be sent.

**Note:** The `sendMessage` method in the `TelegramBot` class uses the `execute` method from the `TelegramLongPollingBot` class to send the message to Telegram. Any exceptions raised during the execution are ignored.

**Note:** The `CurrencyModel` class is not provided in the code snippet, so its properties and structure are not documented here.

This documentation provides an overview of the `CurrencyService` and `TelegramBot` classes and their respective methods. For a complete implementation, ensure that the necessary dependencies, such as the Telegram API library and the `CurrencyModel` class, are available and correctly implemented. Additionally, remember to handle any further exceptions or edge cases that may arise during real-world usage.
