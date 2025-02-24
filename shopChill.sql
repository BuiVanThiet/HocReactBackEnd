CREATE DATABASE chillInFreeShop;
USE chillInFreeShop;

-- Bảng danh mục sản phẩm
CREATE TABLE category (
    id INT IDENTITY(1,1) PRIMARY KEY,
    code_category NVARCHAR(255) NULL,
    name_category NVARCHAR(255) NULL,
    status INT NULL,
    create_date DATETIME NULL,
    update_date DATETIME NULL
);

-- Bảng nhà sản xuất
CREATE TABLE manufacturer (
    id INT IDENTITY(1,1) PRIMARY KEY,
    code_manufacturer NVARCHAR(255) NULL,
    name_manufacturer NVARCHAR(255) NULL,
    status INT NULL,
    create_date DATETIME NULL,
    update_date DATETIME NULL
);

-- Bảng xuất xứ
CREATE TABLE origin (
    id INT IDENTITY(1,1) PRIMARY KEY,
    code_origin NVARCHAR(255) NULL,
    name_origin NVARCHAR(255) NULL,
    status INT NULL,
    create_date DATETIME NULL,
    update_date DATETIME NULL
);

-- Bảng kích thước
CREATE TABLE size (
    id INT IDENTITY(1,1) PRIMARY KEY,
    code_size NVARCHAR(255) NULL,
    name_size NVARCHAR(255) NULL,
    status INT NULL,
    create_date DATETIME NULL,
    update_date DATETIME NULL
);

-- Bảng màu sắc
CREATE TABLE color (
    id INT IDENTITY(1,1) PRIMARY KEY,
    code_color NVARCHAR(255) NULL,
    name_color NVARCHAR(255) NULL,
    status INT NULL,
    create_date DATETIME NULL,
    update_date DATETIME NULL
);

-- Bảng loại trọng lượng
CREATE TABLE weight_type (
    id INT IDENTITY(1,1) PRIMARY KEY,
    code_weight_type NVARCHAR(255) NULL,
    name_weight_type NVARCHAR(255) NULL,
    status INT NULL,
    create_date DATETIME NULL,
    update_date DATETIME NULL
);

-- Bảng vai trò
CREATE TABLE role (
    id INT IDENTITY(1,1) PRIMARY KEY,
    code_role NVARCHAR(255) NULL,
    name_role NVARCHAR(255) NULL,
    status INT NULL,
    create_date DATETIME NULL,
    update_date DATETIME NULL
);

-- Bảng sản phẩm
CREATE TABLE product (
    id INT IDENTITY(1,1) PRIMARY KEY,
    code_product NVARCHAR(255) NULL,
    name_product NVARCHAR(255) NULL,
    id_manufacturer INT NULL,
    id_origin INT NULL,
    id_category INT NULL,
    describe NVARCHAR(MAX) NULL,
    status INT NULL,
    create_date DATETIME NULL,
    update_date DATETIME NULL,
    CONSTRAINT FK_product_manufacturer FOREIGN KEY (id_manufacturer) REFERENCES manufacturer(id),
    CONSTRAINT FK_product_origin FOREIGN KEY (id_origin) REFERENCES origin(id),
    CONSTRAINT FK_product_category FOREIGN KEY (id_category) REFERENCES category(id)
);

-- Bảng chi tiết sản phẩm
CREATE TABLE product_detail (
    id INT IDENTITY(1,1) PRIMARY KEY,
    id_product INT NULL,
    id_size INT NULL,
    id_color INT NULL,
    id_weight_type INT NULL,
    import_price DECIMAL(18,2) NULL,
    selling_price DECIMAL(18,2) NULL,
    quantity DECIMAL(18,2) NULL,
    weight DECIMAL(18,2) NULL,
    create_date DATETIME NULL,
    update_date DATETIME NULL,
	status INT NULL,
    CONSTRAINT FK_product_detail_product FOREIGN KEY (id_product) REFERENCES product(id),
    CONSTRAINT FK_product_detail_size FOREIGN KEY (id_size) REFERENCES size(id),
    CONSTRAINT FK_product_detail_color FOREIGN KEY (id_color) REFERENCES color(id),
    CONSTRAINT FK_product_detail_weight_type FOREIGN KEY (id_weight_type) REFERENCES weight_type(id)
);

create table image_product(
	id INT IDENTITY(1,1) NOT NULL,
	id_product INT NOT NULL,
	code_image NVARCHAR(255) NULL,
	status int NULL,
	create_date datetime NULL,
	update_date datetime NULL,
	CONSTRAINT FK_image_product_product FOREIGN KEY (id_product) REFERENCES product(id)
)

CREATE TABLE users (
    id INT IDENTITY(1,1) PRIMARY KEY,
	code_user NVARCHAR(255) NULL,
    full_name NVARCHAR(255) NULL,
	birth_day DATE NULL,
	image nvarchar(MAX),
	gender INT NULL,
	email nvarchar(255) null,
	number_phone nvarchar(255) null,
	account nvarchar(255) null,
	role nvarchar(255) null,
    password NVARCHAR(MAX) NULL,
    status INT NULL,
    create_date DATETIME NULL,
    update_date DATETIME NULL
);
INSERT INTO users (code_user, full_name, birth_day, image, gender, email, number_phone, account, password, status, create_date, update_date,role)  
VALUES ('U123456', N'Nguyễn Văn A', '1998-05-20', 'avatar.jpg', 1, 'nguyenvana@example.com', '0987654321', 'nguyenvana', 'hashed_password', 1, GETDATE(), GETDATE(),'ADMIN');

CREATE TABLE blacklist_tokens (
	id INT IDENTITY(1,1) NOT NULL,
	token NVARCHAR(MAX) NULL,
	expiredAt DATETIME NULL,
	status int NULL,
	create_date datetime NULL,
	update_date datetime NULL,
)

INSERT INTO users (first_name, last_name, login, password, status, create_date, update_date)  
VALUES  
(N'Nguyễn', N'Văn A', N'nguyenvana', HASHBYTES('SHA2_256', '123456'), 1, GETDATE(), GETDATE());
/*
create table product(
	id INT IDENTITY(1,1) NOT NULL,
	status int NULL,
	create_date datetime NULL,
	update_date datetime NULL,
)