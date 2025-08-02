# Content Sorter

Сканирует файлы построчно, сортирует по типу (int, String, float) и записывает в новые файлы для каждого типа.

## Требования: 
- Language: Java 21
- Build: Apache Maven 3.9.9 + Maven Shade Plugin (fat jar)
- Third party libraries: picocli 4.7.7 https://mvnrepository.com/artifact/info.picocli/picocli/4.7.7

## Запуск:
- Собрать с помощью Maven или использовать jar из Release
- Пример запуска в командной строке: java -jar util.jar -o customPath -p prefix_ -f in1.txt in2.txt
