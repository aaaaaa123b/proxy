## Использование
1) Указать аннотацию @Proxy над прокси классом 
2) Указать аннотацию @Component над проксируемым классом
3) Указать аннотацию @Log над методами класса,которые нужно логировать

## Условия задания
Написать аннотацию @Log, которою можно указывать на методами класса.
Если над методом стоит аннотация, то при его вызове в консоль должен выводиться лог о его начале и завершении (с указанием названия класса и метода).
Для реализации аннотации необходимо использовать паттерн "Прокси", поэтому для логирования класс должен иметь интерфейс.
Требуется соблюдать принципы SOLID и по возможности использовать другие паттерны.