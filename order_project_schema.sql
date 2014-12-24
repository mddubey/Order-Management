drop table order_product;
drop table orders;
drop table customers;
drop table products;
 
 create table customers(
  cust_id integer PRIMARY KEY auto_increment,
  cust_name VARCHAR(30) not null,
  address varchar(100)
 );
 
 ALTER TABLE customers AUTO_INCREMENT=101;

 
 create table products(
  prod_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  prod_name varchar(20) not null,
  quantity INTEGER not null,
  price integer not null
 );
 
 
create table orders(
  order_id integer PRIMARY KEY AUTO_INCREMENT,
  cust_id INTEGER not null,
  order_date date not null,
  total integer not null
);

 ALTER TABLE ORDERS AUTO_INCREMENT=1001;

alter TABLE orders add foreign key(cust_id) references customers(cust_id);

create table order_product(
  id integer primary key auto_increment,
  order_id integer not null,
  product_id integer not null,
  quantity integer not null,
  total integer not null 
);

alter TABLE order_product add foreign key(order_id) references orders(order_id);
alter TABLE order_product add foreign key(product_id) references products(prod_id);

show tables;


