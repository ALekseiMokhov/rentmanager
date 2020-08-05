USE senla;

-- Задание: 1 Найдите номер модели, скорость и размер жесткого диска для всех ПК стоимостью менее 500 дол. Вывести: model, speed и hd
SELECT model, speed, hd FROM pc WHERE price < 500;

-- Задание: 2 Найдите производителей принтеров. Вывести: maker
SELECT DISTINCT maker FROM product p 
 INNER JOIN printer pr on p.model = pr.model;

-- Задание: 3 Найдите номер модели, объем памяти и размеры экранов ПК-блокнотов, цена которых превышает 1000 дол.
SELECT model, ram, screen 
FROM laptop WHERE price > 1000 ;

-- Задание: 4 Найдите все записи таблицы Printer для цветных принтеров.
SELECT * FROM printer WHERE color ='y';

-- Задание: 5 Найдите номер модели, скорость и размер жесткого диска ПК, имеющих 12x или 24x CD и цену менее 600 дол
SELECT model, speed, hd FROM pc 
WHERE (price < 600 AND (cd = '12X' OR cd = '24X' ));

-- Задание: 6 Укажите производителя и скорость для тех ПК-блокнотов, которые имеют жесткий диск объемом не менее 100 Гбайт.
SELECT maker, speed FROM laptop l 
INNER JOIN product p ON l.model = p.model 
WHERE l.hd>=100;

-- Задание: 7 Найдите номера моделей и цены всех продуктов (любого типа), выпущенных производителем B (латинская буква) - // решил не переделывать весь инсерт, выбрал "содержащих" B
SELECT l.model, price FROM laptop l 
INNER JOIN product p ON p.model = l.model 
WHERE p.maker LIKE '%B%'
UNION
SELECT pc.model, price FROM pc  
INNER JOIN product p ON p.model = pc.model 
WHERE p.maker LIKE '%B%'
UNION
SELECT pr.model, price FROM printer pr 
INNER JOIN product p ON p.model = pr.model 
WHERE p.maker LIKE '%B%';

-- Задание: 8 Найдите производителя, выпускающего ПК, но не ПК-блокноты.
SELECT DISTINCT maker FROM product 
INNER JOIN pc ON pc.model = product.model 
WHERE maker NOT IN (SELECT maker from product p INNER JOIN laptop l ON p.model = l.model);

-- Задание: 9 Найдите производителей ПК с процессором не менее 450 Мгц. Вывести: Maker
SELECT DISTINCT maker FROM product 
INNER JOIN  pc ON pc.model=product.model 
WHERE pc.speed > 450;

-- Задание: 10 Найдите принтеры, имеющие самую высокую цену. Вывести: model, price
SELECT model,price 
FROM printer WHERE price IN (SELECT MAX(price) FROM printer);

-- Задание: 11 Найдите среднюю скорость ПК.
SELECT AVG(speed) speed  FROM pc;

-- Задание: 12 Найдите среднюю скорость ПК-блокнотов, цена которых превышает 1000 дол.
SELECT AVG(speed) speed FROM laptop 
WHERE price > 1000;

-- Задание: 13 Найдите среднюю скорость ПК, выпущенных производителем A. // тоже,выбрал HP
SELECT AVG(speed) speed FROM laptop l 
INNER JOIN product p ON l.model = p.model 
WHERE p.maker = 'HP';

-- Задание: 14 Для каждого значения скорости найдите среднюю стоимость ПК с такой же скоростью процессора. Вывести: скорость, средняя цена
SELECT DISTINCT speed, AVG(price) price 
FROM pc GROUP BY speed ;

-- Задание: 15 Найдите размеры жестких дисков, совпадающих у двух и более PC. Вывести: HD
SELECT DISTINCT hd FROM pc p1 
WHERE EXISTS(SELECT hd FROM pc p2 WHERE p1.hd=p2.hd AND p1.model <> p2.model);

-- Задание: 16 Найдите пары моделей PC, имеющих одинаковые скорость и RAM. /
-- В результате каждая пара указывается только один раз, т.е. (i,j), но не (j,i), Порядок вывода: модель с большим номером, модель с меньшим номером, скорость и RAM.
SELECT DISTINCT pc1.model model_1, pc2.model model_2, pc1.speed, pc2.ram FROM pc pc1 , pc pc2 
WHERE pc1.speed = pc2.speed AND pc2.ram = pc1.ram AND pc1.code>pc2.code ;

-- Задание: 17 Найдите модели ПК-блокнотов, скорость которых меньше скорости любого из ПК. 
SELECT type, l.model, speed FROM laptop l 
JOIN product p ON l.model = p.model  
WHERE l.speed < (SELECT MIN(speed) FROM pc);

-- Задание: 18 Найдите производителей самых дешевых цветных принтеров. Вывести: maker, price
SELECT maker,price FROM Product p
JOIN Printer p1 ON p1.model=p.model
WHERE price IN (
SELECT MIN(price) FROM Printer
WHERE color ='y' )
AND color ='y';

-- Задание: 19 Для каждого производителя найдите средний размер экрана выпускаемых им ПК-блокнотов. Вывести: maker, средний размер экрана.
SELECT DISTINCT maker, AVG(screen) screen FROM laptop l 
INNER JOIN product p ON l.model = p.model 
GROUP BY maker;

-- Задание: 20 Найдите производителей, выпускающих по меньшей мере три различных модели ПК. Вывести: Maker, число моделей
SELECT DISTINCT maker , COUNT(pc.model) count FROM pc 
INNER JOIN product p ON p.model = pc.model 
GROUP BY maker 
HAVING count >2;

-- Задание: 21 Найдите максимальную цену ПК, выпускаемых каждым производителем. Вывести: maker, максимальная цена.
SELECT maker, MAX(price) FROM product p 
INNER JOIN pc ON pc.model = p.model 
GROUP BY maker;

-- Задание: 22 Для каждого значения скорости ПК, превышающего 600 МГц, определите среднюю цену ПК с такой же скоростью. Вывести: speed, средняя цена.
SELECT DISTINCT speed , AVG( price)  FROM pc 
GROUP BY speed;

-- Задание: 23 Найдите производителей, которые производили бы как ПК со скоростью не менее 750 МГц, так и ПК-блокноты со скоростью не менее 750 МГц. Вывести: Maker
Select maker FROM product p
JOIN pc ON pc.model = p.model
WHERE pc.speed>=750
AND 
maker IN (Select maker FROM product p
JOIN laptop l ON l.model = p.model
WHERE l.speed>=750);

-- Задание: 24 Перечислите номера моделей любых типов, имеющих самую высокую цену по всей имеющейся в базе данных продукции. // Воркбенч ругается на выборку через WITH одним запросом(версия 5.7)
WITH all_products AS(
SELECT model,price FROM PC
UNION
SELECT model, price FROM LAptop
UNION
SELECT model, price FROM Printer)

SELECT model FROM all_products a
WHERE a.price = (SELECT MAX(price) FROM all_products);
-- выборка через 2
CREATE OR REPLACE VIEW all_products AS
SELECT model,price FROM PC
UNION
SELECT model, price FROM LAptop
UNION
SELECT model, price FROM Printer;

SELECT model FROM all_products a
WHERE a.price = (SELECT MAX(price) FROM all_products);
;

-- Задание: 25 Найдите производителей принтеров, которые производят ПК с наименьшим объемом RAM 
-- и с самым быстрым процессором среди всех ПК, имеющих наименьший объем RAM. Вывести: Maker

SELECT DISTINCT maker FROM product
WHERE model IN (SELECT model FROM pc WHERE ram = (SELECT MIN(ram) FROM pc)
AND speed = (SELECT MAX(speed) FROM pc WHERE ram = (SELECT MIN(ram) FROM pc)))
AND
maker IN (
SELECT maker FROM product
WHERE type='printer'
);


 

