		#1

SELECT model,speed,hd
FROM pc
WHERE price < 500


		#2 

		SELECT DISTINCT maker
FROM product
WHERE type ='Printer'

		
		#3

SELECT MODEL, RAM, SCREEN
FROM laptop
WHERE price > 1000


		#4

		SELECT *
		FROM printer
		WHERE color = 'y'


		#5

		SELECT model, speed, hd
		FROM pc
		WHERE (cd = '12x' OR cd = '24x') AND price < 600


		#6

		SELECT DISTINCT maker, speed
		FROM product 
		INNER JOIN laptop
		ON product.model=laptop.model
		WHERE  (type = 'laptop' AND hd >= 10)


		#7 

		SELECT a.model, a.price 
		FROM (SELECT model, price from pc
		      UNION
		      SELECT model, price from laptop
		      UNION
		      SELECT model, price from printer) as a
		INNER JOIN product p on p.model = a.model
		WHERE p.maker = 'B'


		#8 

		SELECT DISTINCT maker
		FROM PRODUCT
		WHERE type = 'pc'
		EXCEPT
		SELECT DISTINCT product.maker
		FROM PRODUCT
		WHERE type = 'laptop'

		
		#9
		
		SELECT DISTINCT product.maker
		FROM pc
		INNER JOIN product ON pc.model = product.model
		WHERE pc.speed >= 450
		
		
		#10
		
		SELECT model, price
		FROM PRINTER
		WHERE price = (SELECT MAX (price) FROM printer)

		
		#11
		
		SELECT AVG(speed) as avg_speed
		FROM pc

		
		#12
		
		SELECT AVG(speed)
		FROM laptop
		WHERE price > 1000

		#13
		
		SELECT AVG (pc.speed)
		FROM pc, product
		WHERE pc.model = product.model AND product.maker = 'A'

		
		#14
		
		SELECT s.class, s.name, c.country
		FROM ships s
		LEFT JOIN classes c ON s.class = c.class
		WHERE c.numGuns >= 10

		
		#15
		
		SELECT hd FROM pc 
		GROUP BY (hd) 
		HAVING COUNT (model)>=2

		
		#16
		
		SELECT DISTINCT pc.model, pc2.model, pc.speed, pc.ram  
		FROM pc
		INNER JOIN pc as pc2 ON (pc2.speed=pc.speed) 
		AND (pc2.ram=pc.ram) AND (pc.model!=pc2.model) AND (pc.model>pc2.model)

		
		#17
		
		SELECT DISTINCT p.type, p.model, l.speed 
		FROM Product p
		INNER JOIN Laptop l ON l.speed < ALL (SELECT speed FROM PC)
		WHERE l.model = p.model AND p.type='Laptop'

		
		
		#18
		
		SELECT DISTINCT  p.maker, r.price FROM Printer r 
		INNER JOIN Product p ON p.type='Printer'
		WHERE r.price = (SELECT MIN(price) FROM Printer WHERE color='y') AND p.model=r.model AND r.color='y'

		
		#19
		
		SELECT p.maker, AVG(l.screen) FROM Product p
		INNER JOIN Laptop l ON p.model=l.model
		WHERE p.type='Laptop'
		GROUP BY p.maker

		
		#20
		
		SELECT p.maker, COUNT(p.model) AS Count_Model FROM Product p
		WHERE p.type='PC'
		GROUP BY p.maker
		HAVING COUNT(p.model)>2

		
		#21
		
		
		SELECT maker, MAX(price) as max_price 
		FROM pc 
		JOIN product ON product.model = pc.model
		GROUP BY maker

		
		#22
		
		SELECT pc.speed, AVG(pc.price) AS Avg_price FROM PC pc
		WHERE pc.speed > 600
		GROUP BY pc.speed

		
		#23
		
		SELECT DISTINCT p.maker FROM Product p
		INNER JOIN PC pc ON pc.speed >= 750
		WHERE p.type='PC' AND p.model=pc.model
		INTERSECT
		SELECT DISTINCT p.maker FROM Product p
		INNER JOIN laptop l ON l.speed >= 750
		WHERE p.type='Laptop' AND p.model=l.model

		
		#24
		
		WITH q_t_model(model,price) as(select model,price from pc union
		select model,price from laptop union select model,price from printer)
		select model from q_t_model
		where price = (select max(price) from q_t_model)
		
		
		#25
		
		SELECT maker FROM Product
		WHERE model IN (SELECT model FROM PC
		WHERE speed = (SELECT MAX(speed) FROM PC
		WHERE ram = (SELECT MIN(ram) FROM PC)) AND ram = (SELECT MIN(ram) FROM PC)) AND maker IN (SELECT p.maker FROM Product p WHERE p.type='Printer')
		GROUP BY maker

		
		#26
		
		SELECT AVG(price) AS AVG_price FROM (
		SELECT price FROM Product p 
		INNER JOIN PC ON p.model=PC.model
		WHERE  p.maker = 'A'
		UNION ALL
		SELECT price FROM Product p
		INNER JOIN Laptop l ON p.model=l.model
		WHERE p.maker = 'A') as T
		
		#27
		
		SELECT p.maker AS Maker, AVG(pc.hd) AS Avg_hd FROM Product p
		INNER JOIN PC pc ON pc.model = p.model
		WHERE p.maker IN (SELECT p.maker FROM Product p WHERE p.type='Printer')
		GROUP BY p.maker

		
		#28
		
		SELECT DISTINCT COUNT(maker) AS qty 
		FROM(SELECT maker FROM Product GROUP BY maker HAVING COUNT(DISTINCT model) = 1) X

		
		#29
		
		SELECT t1.point, t1.date, inc, out
		FROM income_o t1 LEFT JOIN outcome_o t2 ON t1.point = t2.point
		AND t1.date = t2.date
		UNION
		SELECT t2.point, t2.date, inc, out
		FROM income_o t1 RIGHT JOIN outcome_o t2 ON t1.point = t2.point
		AND t1.date = t2.date

		
		#30
		
		SELECT point, date, SUM(out) AS out, SUM(inc) AS inc
		FROM (SELECT point, date, out, null AS inc FROM Outcome
		UNION ALL
		SELECT point, date, null AS out, inc FROM Income) as this_table
		GROUP BY point, date

		
		#31
		
		
		SELECT class, country 
		FROM Classes
		WHERE bore >= 16

		
		
		#32

		
		SELECT country, cast(avg((power(bore,3)/2)) as numeric(6,2)) as weight
		FROM (select country, classes.class, bore, name 
		FROM classes 
		LEFT JOIN ships on classes.class=ships.class
		UNION all
		SELECT DISTINCT country, class, bore, ship from classes t1 left join outcomes t2 on t1.class=t2.ship
		WHERE ship=class and ship not in (select name from ships) ) a
		WHERE name IS NOT NULL 
		GROUP BY country

		
		#33
		
		
		SELECT ship 
		FROM Outcomes 
		WHERE result='sunk' AND battle='North Atlantic'

		
		
		#34
		
		
		SELECT DISTINCT s.name 
		FROM Ships s
		INNER JOIN Classes c ON c.type='bb'
		WHERE c.class=s.class AND c.displacement > 35000 AND s.launched>=1922
		AND s.launched IS NOT NULL

		
		
		#35
		
		SELECT model, type
		FROM product
		WHERE upper(model) NOT like '%[^A-Z]%'
		OR model NOT like '%[^0-9]%'

		
		#36
		
		
		SELECT name FROM Ships
		WHERE name=class
		UNION
		SELECT ship AS name FROM Outcomes 
		WHERE ship IN (SELECT class FROM Classes)

		
		#37
		
		SELECT  c.class 
		FROM Classes c
		LEFT JOIN (SELECT class, name 
		FROM Ships
		UNION
		SELECT Classes.class as class, Outcomes.ship as name
		FROM Outcomes
		JOIN Classes ON Outcomes.ship = Classes.class) as s ON c.class = s.class
		GROUP BY c.class
		HAVING count(s.name)=1
		
  
		#38
		
		
		SELECT country FROM Classes
		WHERE type='bb'
		INTERSECT
		SELECT country FROM Classes
		WHERE type='bc'

		
		#39
		
		
		WITH b_s AS
		(SELECT o.ship, b.name, b.date, o.result
		FROM outcomes o
		LEFT JOIN battles b ON o.battle = b.name )
		SELECT DISTINCT a.ship FROM b_s a
		WHERE UPPER(a.ship) IN
		(SELECT UPPER(ship) FROM b_s b
		WHERE b.date < a.date AND b.result = 'damaged')

		
		#40
		
		
		SELECT maker, MAX(type)
		FROM product
		GROUP BY maker
		HAVING COUNT(DISTINCT type) = 1 AND COUNT(model) > 1

		
		
		#41
		
		
		WITH D as
		(SELECT model, price 
		FROM PC
		UNION
		SELECT model, price 
		FROM Laptop
		UNION
		SELECT model, price 
		FROM Printer)
		SELECT distinct P.maker,
		CASE WHEN MAX(CASE WHEN D.price IS NULL THEN 1 ELSE 0 END) = 0 THEN
		MAX(D.price) END
		FROM Product P
		RIGHT JOIN D on P.model=D.model
		GROUP BY P.maker
		
		
		#42
		
		
		SELECT ship, battle 
		FROM outcomes 
		WHERE result = 'sunk'

		
		
		#43
		
		
		
		#44
		
		
		SELECT name 
		FROM ships 
		WHERE name like 'R%'
		UNION
		SELECT ship 
		FROM outcomes 
		WHERE ship like 'R%'

		
		
		#45
		
		
		SELECT name from ships 
		WHERE name like '% % %'
		UNION
		SELECT ship 
		FROM outcomes 
		WHERE ship like '% % %'

		
		
		#46
		
		SELECT DISTINCT ship, displacement, numguns
		FROM classes 
		LEFT JOIN ships ON classes.class=ships.class 
		RIGHT JOIN outcomes ON classes.class=ship OR ships.name=ship
		WHERE battle='Guadalcanal'

		
		
		#47
		
		WITH out AS (SELECT *
		FROM outcomes 
		JOIN (SELECT ships.name s_name, classes.class s_class, classes.country s_country
		FROM ships FULL JOIN classes
		ON ships.class = classes.class
		) u
		ON outcomes.ship=u.s_class
		UNION
		SELECT *
		FROM outcomes 
		JOIN (SELECT ships.name s_name, classes.class s_class, classes.country s_country
		FROM ships 
		FULL JOIN classes
		ON ships.class = classes.class
		) u
		ON outcomes.ship=u.s_name)

		SELECT fin.country
		FROM (
		SELECT DISTINCT t.country, COUNT(t.name) AS num_ships
		FROM (
		select distinct c.country, s.name
		from classes c
		inner join Ships s on s.class= c.class
		union
		select distinct c.country, o.ship
		from classes c
		inner join Outcomes o on o.ship= c.class) t
		GROUP BY t.country
		INTERSECT
		SELECT out.s_country, COUNT(out.ship) AS num_ships
		FROM out
		WHERE out.result='sunk'
		GROUP BY out.s_country) fin

		
		#48
		
		
		SELECT class
		FROM classes t1 
		LEFT JOIN outcomes t2 on t1.class=t2.ship 
		WHERE result='sunk'
		UNION
		SELECT class
		FROM ships 
		LEFT JOIN outcomes on ships.name=outcomes.ship 
		WHERE result='sunk'

		
		#49
		
		
		SELECT s.name 
		FROM ships s 
		JOIN classes c on s.name=c.class or s.class = c.class 
		WHERE c.bore = 16
		UNION
		SELECT o.ship 
		FROM outcomes o join classes c on o.ship=c.class 
		WHERE c.bore = 16
