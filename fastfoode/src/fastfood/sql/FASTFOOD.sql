	CREATE DATABASE FASTFOOD
go

USE FASTFOOD
GO
CREATE TABLE users
(
	USERS VARCHAR(20) PRIMARY KEY,
	PASS VARCHAR(20) NOT NULL,
	NAMES NVARCHAR(30),
	PHONE  CHAR(10),
	EMAIL VARCHAR(60) NOT NULL,
	ADDRESSS  NVARCHAR(100),
	IMAGES  VARCHAR(30),
	BRITH DATE,
	ROLES VARCHAR(20),
	GENDER BIT,
)
	

GO

CREATE TABLE USERSTORE(
	USERS VARCHAR(20) PRIMARY KEY REFERENCES users(USERS),
	NOTE NVARCHAR(50)
)
GO
CREATE TABLE STORE(
	IDSTORE INT IDENTITY(1,1) PRIMARY KEY,
	NAMES NVARCHAR(50),
	IDUSER VARCHAR(20) REFERENCES users(USERS)
)

GO
CREATE TABLE TYPEPRODUCT(
	TYPEPRODUCT INT IDENTITY(1,1) PRIMARY KEY,
	TYPENAMEPRODUCT NVARCHAR(50)
)

GO

CREATE TABLE PRODUCT(
	IDPRODUCT int identity(1,1) primary key,
	IDSTORE INT REFERENCES STORE(IDSTORE),
	TYPEPRODUCT INT REFERENCES TYPEPRODUCT(TYPEPRODUCT),
	NAMES NVARCHAR(50),
	PRICE MONEY,
	QUANTITY INT,
	IMAGES nvarchar(40),
	NOTE NVARCHAR(50),
	EXISTSS BIT DEFAULT 1
)

GO
CREATE TABLE CART(
	USERS VARCHAR(20) REFERENCES users(USERS),
	IDPRODUCT INT REFERENCES PRODUCT(IDPRODUCT),
	QUANTITY INT,
	PRIMARY KEY(USERS,IDPRODUCT)
)

GO
CREATE TABLE BILL(
	IDBILL INT IDENTITY(1,1) PRIMARY KEY,
	USERS VARCHAR(20) references users(USERS),
	IDPRODUCT INT references PRODUCT(IDPRODUCT),
	DATEPURCHASE DATE DEFAULT GETDATE(),
	QUANTITY INT,
	PRICE MONEY,

)


go
create table Tim(
	USERS VARCHAR(20) REFERENCES users(USERS),
	IDPRODUCT INT references PRODUCT(IDPRODUCT),
	LIKES BIT DEFAULT 1,
	PRIMARY KEY (USERS,IDPRODUCT)
)


go
create table CommentOne(
	IDComment int identity(1,1) primary key,
	USERS varchar(20) references USERS(USERS),
	IDProduct int references PRODUCT(IDPRODUCT),
	Content nvarchar(300),
	DateComment Date Default getDate(),
	viTri bit default 0,
	tacGia nvarchar(20)
)


go
create table LikeCommentOne(
	IDComment int references CommentOne(IDComment),
	USERS varchar(20) references USERS(USERS),
	likes int,
	primary key(IDComment,USERS)
)

go
create table CommentTwo(
	IDCommentTwo int identity(1,1) primary key,
	IDComment int references CommentOne(IDComment),
	USERS varchar(20) references USERS(USERS),
	Content varchar(300),
	DateComment Date Default getDate(),
	tacGia nvarchar(20)
)

go
create table LikeCommentTwo(
	IDCommentTwo int references CommentTwo(IDCommentTwo),
	USERS varchar(20) references USERS(USERS),
	likes int,
	primary key(IDCommentTwo,USERS)
)


--go
--create table Moneys
--(
--	USERS varchar(20) references USERS(USERS),
--	moneys float default 0.0,
--	primary key(USERS)
--)

--drop table Moneys
--go
--drop trigger if exists update_moneys
--GO
--create trigger update_moneys on users for insert 
--as
--begin
--	insert into Moneys values((select users from inserted),0)
--end
--go

drop trigger if exists update_Quantity_SP
GO
create trigger update_Quantity_SP on PRODUCT for UPDATE
AS
BEGIN
	IF EXISTS (SELECT * FROM inserted WHERE QUANTITY <= 0)
		BEGIN
			ROLLBACK TRANSACTION
		END
END












--tài khoản chạy sẵn 

--tài khoản admin
insert into users(USERS,PASS,ROLES,EMAIL) values ('Admin12345','Admin12345','admin','doanhuynh601@gmail.com')

--tài khoản khách hàng chờ nâng cấp thành store
insert users(USERS,PASS,ROLES,EMAIL) values ('Customer12345','Customer12345','user','nhduycuong16011601@gmail.com')
select * from USERSTORE
--Khách hàng gửi thông báo muốn nâng cấp tài khoản thành store
insert into USERSTORE values ('Customer12345','Tôi muốn bán hành cho tôi bán với')

--admin đồng ý cho bán hàng
exec confirm 'Customer12345',1


--dùng tài khoản Customer12345 để thêm sửa xóa sản phẩm



insert into users(USERS,PASS,ROLES,EMAIL) values
('Customer12346','Customer12345','user','doanuycuong16011601@gmail.com'),
('Customer12347','Customer12345','user','doanhycuong16011601@gmail.com'),
('Customer12348','Customer12345','user','doacuong16011601@gmail.com'),
('Customer12349','Customer12345','user','doaycuong16011601@gmail.com'),
('Customer123456','Customer12345','user','doanhuyncuong16011601@gmail.com')


insert into USERSTORE values 
('Customer12346','Tôi muốn bán hành cho tôi bán với'),
('Customer12347','Tôi muốn bán hành cho tôi bán với')