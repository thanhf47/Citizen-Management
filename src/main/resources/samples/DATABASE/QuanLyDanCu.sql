--b1:
CREATE DATABASE QUANLYDANCU;
----------------------------
--B2:
USE QuanLyDanCu

--B3:
---------------------------------------------------------------------------------------------
CREATE TABLE NGUOIQUANLY(
	HOTEN NVARCHAR(50) NOT NULL,
	TENDANGNHAP VARCHAR(50) NOT NULL UNIQUE,
	MATKHAU VARCHAR(20) NOT NULL,
	SODIENTHOAI CHAR(15) NOT NULL,
	VAITRO BIT NOT NULL
);
---------------------------------------------------------------------------------------------

CREATE TABLE NHANKHAU(
	MANHANKHAU INT IDENTITY(1, 1),
	HOTEN NVARCHAR(50) NOT NULL,
	SOCANCUOC VARCHAR(15),
	NGAYSINH DATE NOT NULL,
	GIOITINH BIT NOT NULL,
	NOISINH NVARCHAR(200) NOT NULL,
	NGUYENQUAN NVARCHAR(200) NOT NULL,
	DANTOC NVARCHAR(20) NOT NULL,
	TONGIAO NVARCHAR(20) NOT NULL,
	QUOCTICH NVARCHAR(20) NOT NULL,
	NOITHUONGTRU NVARCHAR(200),
	NGHENGHIEP NVARCHAR(100),
	NGAYTAO DATE,
	GHICHU NVARCHAR(200),

	PRIMARY KEY (MANHANKHAU)
);
---------------------------------------------------------------------------------------------

CREATE TABLE HOKHAU(
	MAHOKHAU INT IDENTITY(1, 1),
	IDCHUHO INT NOT NULL,
	DIACHI NVARCHAR(200) NOT NULL,
	NGAYTAO DATE,
	GHICHU NVARCHAR(200),

	TENCHUHO NVARCHAR(300),

	PRIMARY KEY (MAHOKHAU),
	FOREIGN KEY (IDCHUHO) REFERENCES NHANKHAU(MANHANKHAU)
);


CREATE TABLE THANHVIENCUAHO(
	MANHANKHAU INT NOT NULL,
	MAHOKHAU INT NOT NULL,
	QUANHEVOICHUHO NVARCHAR(100) NOT NULL,

	PRIMARY KEY(MANHANKHAU, MAHOKHAU),
	FOREIGN KEY(MANHANKHAU) REFERENCES NHANKHAU(MANHANKHAU),
	FOREIGN KEY(MAHOKHAU) REFERENCES HOKHAU(MAHOKHAU),
);


CREATE PROC INSERT_HOKHAU 
@IDCHUHO INT, @DIACHI NVARCHAR(300), @NGAYTAO DATE, @GHICHU NVARCHAR(200)
AS
BEGIN
	INSERT INTO HOKHAU(IDCHUHO, DIACHI, NGAYTAO, GHICHU)
	VALUES (@IDCHUHO, @DIACHI, @NGAYTAO, @GHICHU)

	DECLARE @TENCHUHO NVARCHAR(300), @MAHOKHAU INT
	SELECT @MAHOKHAU = MAX(MAHOKHAU) FROM HOKHAU

	INSERT INTO THANHVIENCUAHO(MANHANKHAU, MAHOKHAU, QUANHEVOICHUHO)
	VALUES (@IDCHUHO, @MAHOKHAU, N'Chủ hộ');

	SELECT @TENCHUHO = HOTEN FROM NHANKHAU WHERE MANHANKHAU = @IDCHUHO

	UPDATE HOKHAU
	SET TENCHUHO = @TENCHUHO
	WHERE MAHOKHAU = @MAHOKHAU
END

---------------------------------------------------------------------------------------------

CREATE TABLE TAMTRU(
	MAGIAYTAMTRU INT IDENTITY(1, 1),
	MANHANKHAU INT NOT NULL,
	SODIENTHOAINGUOIDANGKY VARCHAR(15) NOT NULL,
	TUNGAY DATE NOT NULL,
	DENNGAY DATE NOT NULL,
	LYDO NVARCHAR(300),
	
	PRIMARY KEY (MAGIAYTAMTRU, MANHANKHAU),
	FOREIGN KEY (MANHANKHAU) REFERENCES NHANKHAU(MANHANKHAU)
);

----------------------------------------------------------------------------------------------

CREATE TABLE TAMVANG(
	MAGIAYTAMVANG INT IDENTITY(1, 1),
	MANHANKHAU INT NOT NULL,
	NOITAMTRU NVARCHAR(300) NOT NULL,
	TUNGAY DATE NOT NULL,
	DENNGAY DATE NOT NULL,
	LYDO NVARCHAR(300),

	PRIMARY KEY (MAGIAYTAMVANG, MANHANKHAU),
	FOREIGN KEY (MANHANKHAU) REFERENCES NHANKHAU(MANHANKHAU)
);

-----------------------------------------------------------------------------------------------
CREATE TABLE KHAITU(
	MAGIAYKHAITU INT IDENTITY(1, 1),
	MANHANKHAUNGUOIKHAI INT NOT NULL,
	MANHANKHAUNGUOICHET INT NOT NULL,
	NGAYKHAI DATE,
	NGAYCHET DATE,
	LYDOCHET NVARCHAR(300),

	PRIMARY KEY(MAGIAYKHAITU, MANHANKHAUNGUOIKHAI, MANHANKHAUNGUOICHET),
	FOREIGN KEY(MANHANKHAUNGUOIKHAI) REFERENCES NHANKHAU(MANHANKHAU),
	FOREIGN KEY(MANHANKHAUNGUOICHET) REFERENCES NHANKHAU(MANHANKHAU)
);
----------------------------------------------------------------------------------------------

ALTER TABLE KHAITU
ADD CONSTRAINT UQ_MANHANKHAUNGUOICHET UNIQUE (MANHANKHAUNGUOICHET);

----------------------------------------------------------------------------------------------

CREATE PROC DELETE_NHANKHAU
@MANHANKHAU INT, @OUTPUT INT OUTPUT
AS
BEGIN
	IF (@MANHANKHAU NOT IN (SELECT IDCHUHO FROM HOKHAU))
	BEGIN 
		DELETE FROM TAMTRU WHERE MANHANKHAU = @MANHANKHAU 
		DELETE FROM TAMVANG WHERE MANHANKHAU = @MANHANKHAU 
		DELETE FROM KHAITU WHERE MANHANKHAUNGUOICHET = @MANHANKHAU
		DELETE FROM THANHVIENCUAHO WHERE MANHANKHAU = @MANHANKHAU 
		DELETE FROM NHANKHAU WHERE MANHANKHAU = @MANHANKHAU
		SELECT @OUTPUT = 1
	END
	ELSE SELECT @OUTPUT = 0;
END
--------------------------------------------------------------------------------------

INSERT INTO Nguoiquanly (HOTEN, TENDANGNHAP, MATKHAU, SODIENTHOAI, VAITRO)
VALUES
    (N'Nguyễn Tiến Thành', 'thanh', 'thanh', '0123456789', 1),
    (N'Từ Văn An', 'an', 'an', '0987654321', 1),
    (N'Lương Trung Kiên', 'kien', 'kien', '0123456788', 0),
    (N'Quách Đình Dương', 'duong', 'duong', '0122222222', 1),
    (N'Thiều Văn Dũng', 'dung', 'dung', '999993333', 0);
 
--------------------------------------------------------------------------------------

INSERT INTO NHANKHAU (
	HOTEN, 
	SOCANCUOC,
	NGAYSINH, 
	GIOITINH, 
	NOISINH, 
	NGUYENQUAN, 
	DANTOC, 
	TONGIAO, 
	QUOCTICH
)
VALUES 
	(N'Nguyễn Tiến Thành','027213504397', '2003-07-04', 1, N'Bắc Ninh', N'Bắc Ninh', N'Kinh', N'Không', N'Việt Nam'),
	(N'Quách Đình Dương','349871236984', '2003-01-01', 1, N'Thái Bình', N'Thái Bình', N'Kinh', N'Không', N'Việt Nam'),
	(N'Lương Trung Kiên', '567823490817','2003-01-01', 1, N'Thái Bình', N'Thái Bình', N'Kinh', N'Không', N'Việt Nam'),
	(N'Từ Văn An', '129384756209', '2003-01-01', 1, N'Bắc Giang', N'Bắc Giang', N'Kinh', N'Không', N'Việt Nam'),
	(N'Thiều Văn Dũng', '674502983176', '2003-01-01', 1, N'Bắc Giang', N'Bắc Giang', N'Kinh', N'Không', N'Việt Nam'),
	(N'Nguyễn Văn Vegeta', '987654321098', '1995-04-12', 1, N'Hà Nội', N'Hà Nội', N'Kinh', N'Không', N'Việt Nam'),
	(N'Nguyễn Thị Gojo', '238756190824', '1998-07-24', 0, N'Hải Phòng', N'Hải Phòng', N'Kinh', N'Không', N'Việt Nam'),
	(N'Lê Văn Sukuna', '509812347665', '2000-05-04', 1, N'Thái Nguyên', N'Thái Nguyên', N'Kinh', N'Không', N'Việt Nam'),
	(N'Lê Thị John Wick', '769083451234', '1990-11-06', 0, N'Hà Nam', N'Hà Nam', N'Kinh', N'Không', N'Việt Nam'),
	(N'Trần Văn Neyma', '120938476583', '1988-03-15', 1, N'Hải Dương', N'Hải Dương', N'Kinh', N'Không', N'Việt Nam'),
	(N'Nguyễn Thanh Tùng', '498172365094', '1993-09-22', 1, N'Thái Bình', N'Thái Bình', N'Kinh', N'Không', N'Việt Nam'),
	(N'Nguyễn Thúc Thùy Tiên', '765890231847', '1996-02-18', 0, N'Quảng Ninh', N'Quảng Ninh', N'Kinh', N'Không', N'Việt Nam'),
	(N'Lê Văn Rhymastic', '237456890124', '2002-10-30', 1, N'Nam Định', N'Nam Định', N'Kinh', N'Không', N'Việt Nam'),
	(N'Lê Thị Binz', '654321098765', '1985-08-12', 0, N'Hưng Yên', N'Hưng Yên', N'Kinh', N'Không', N'Việt Nam'),
	(N'Trần Văn Billgate', '890234567123', '1992-06-28', 1, N'Vĩnh Phúc', N'Vĩnh Phúc', N'Kinh', N'Không', N'Việt Nam'),
	(N'Phạm Văn Hitman', '345098172634', '1997-04-11', 1, N'Phú Thọ', N'Phú Thọ', N'Kinh', N'Không', N'Việt Nam'),
	(N'Phạm Thị chatGPT', '432765098124', '1999-12-27', 0, N'Thái Bình', N'Thái Bình', N'Kinh', N'Không', N'Việt Nam'),
	(N'Hoàng Văn Null', '109823745876', '1989-01-27', 1, N'Bắc Kạn', N'Bắc Kạn', N'Kinh', N'Không', N'Việt Nam'),
	(N'Hoàng Thị Obama', '876501239284', '2001-09-15', 0, N'Hòa Bình', N'Hòa Bình', N'Kinh', N'Không', N'Việt Nam'),
	(N'Vũ Văn messi', '321654098723', '1987-07-09', 1, N'Thanh Hóa', N'Thạch Thành', N'Kinh', N'Không', N'Việt Nam'),
	(N'Nguyễn Văn Anh', '928734501267', '1995-03-01', 1, N'Hà Nội', N'Hải Phòng', N'Kinh', N'Không', N'Việt Nam'),
	(N'Nguyễn Thị Bình', '456789012345', '1998-10-23', 0, N'Hải Phòng', N'Hà Nội', N'Tày', N'Không', N'Việt Nam'),
	(N'Lê Văn Cường', '102938475601', '2000-07-16', 1, N'Thái Nguyên', N'Thái Nguyên', N'Mường', N'Không', N'Việt Nam'),
	(N'Lê Thị Dung', '789012345678', '1990-01-01', 0, N'Hà Nam', N'Hồ Chí Minh', N'Khơ Mú', N'Công giáo', N'Việt Nam'),
	(N'Trần Văn Eo', '234567890123', '1988-01-02', 1, N'Hải Dương', N'Thái Bình', N'H''Mông', N'Không', N'Việt Nam'),
	(N'Phạm Thị Lan', '567890123456', '1993-01-03', 0, N'Quảng Ninh', N'Bình Dương', N'Hoà Bình', N'Buddhist', N'Việt Nam'),
	(N'Trịnh Văn Thành', '890123456789', '1987-01-04', 1, N'Nghệ An', N'Hà Giang', N'Thái', N'Protestant', N'Việt Nam'),
	(N'Hoàng Thị Linh', '123456789012', '2002-01-05', 0, N'Quảng Bình', N'Hà Nội', N'Khmer', N'Không', N'Việt Nam'),
	(N'Nguyễn Văn Hùng', '345678901234', '1991-01-06', 1, N'Hà Tĩnh', N'Tiền Giang', N'Tày', N'Buddhist', N'Việt Nam'),
	(N'Trần Thị Kim', '901234567890', '1995-01-07', 0, N'Vĩnh Phúc', N'Phú Thọ', N'Mường', N'Protestant', N'Việt Nam'),
	(N'Nguyễn Văn white', NULL, '2012-09-10', 1, N'Thái Bình', N'Hải Phòng', N'Kinh', N'Không', N'Việt Nam'),
	(N'Nguyễn Thị batman', NULL, '2011-07-15', 0, N'Nam Định', N'Hà Nội', N'Kinh', N'Không', N'Việt Nam'),
	(N'Trần Văn superman', '987654321012', '1998-03-20', 1, N'Hải Phòng', N'Hải Dương', N'Kinh', N'Không', N'Việt Nam'),
	(N'Trần Thị spiderman', NULL, '2013-06-05', 0, N'Hưng Yên', N'Hưng Yên', N'Kinh', N'Không', N'Việt Nam'),
	(N'Phạm Văn wonder woman', '876543210123', '1995-11-25', 1, N'Hải Dương', N'Thái Bình', N'Kinh', N'Không', N'Việt Nam'),
	(N'Phạm Thị songoku', NULL, '2014-01-18', 0, N'Quảng Ninh', N'Bình Dương', N'Kinh', N'Không', N'Việt Nam'),
	(N'Trịnh Văn ronaldo', '765432109234', '1992-08-15', 1, N'Nghệ An', N'Hà Giang', N'Kinh', N'Không', N'Việt Nam'),
	(N'Trịnh Thị luffy', NULL, '2010-12-03', 0, N'Nghệ An', N'Hà Giang', N'Kinh', N'Không', N'Việt Nam'),
	(N'Hoàng Văn Ill', '654321098345', '1999-05-22', 1, N'Quảng Bình', N'Hà Nội', N'Kinh', N'Không', N'Việt Nam'),
	(N'Hoàng Thị Kante', NULL, '2012-11-08', 0, N'Quảng Bình', N'Hà Nội', N'Kinh', N'Không', N'Việt Nam'),
	(N'Nguyễn Văn Linda', '543210987456', '1997-04-27', 1, N'Hà Tĩnh', N'Tiền Giang', N'Kinh', N'Không', N'Việt Nam'),
	(N'Nguyễn Thị Mane', NULL, '2010-08-12', 0, N'Hà Tĩnh', N'Tiền Giang', N'Kinh', N'Không', N'Việt Nam'),
	(N'Trần Văn None', '432109876567', '1994-03-30', 1, N'Vĩnh Phúc', N'Phú Thọ', N'Kinh', N'Không', N'Việt Nam'),
	(N'Trần Thị Puka', NULL, '2012-10-25', 0, N'Vĩnh Phúc', N'Phú Thọ', N'Kinh', N'Không', N'Việt Nam'),
	(N'Phạm Văn Qi', '321098765678', '1991-08-05', 1, N'Thanh Hóa', N'Thạch Thành', N'Kinh', N'Không', N'Việt Nam'),
	(N'Phạm Thị Su', NULL, '2013-03-15', 0, N'Thanh Hóa', N'Thạch Thành', N'Kinh', N'Không', N'Việt Nam'),
	(N'Lê Văn Zoro', '210987654789', '1996-01-01', 1, N'Hà Giang', N'Nam Định', N'Kinh', N'Không', N'Việt Nam'),
	(N'người được chọn', '000000000', '2000-01-01', 1, N'Thái Bình', N'Thái Bình', N'lksd', N'Không', N'Việt Nam'),
	(N'Nguyễn Văn Poe', '000000001', '2021-01-01', 1, N'Lạng Sơn', N'Sơn La', N'bot', N'Không', N'Việt Nam');

----------------------------------------------------------------------------------------

EXEC INSERT_HOKHAU 1, N'Tiên Du, Bắc Ninh', NULL, NULL
EXEC INSERT_HOKHAU 2, N'Số 1 Đại Cồ Việt, Hai Bà Trưng, Hà Nội', NULL, NULL
EXEC INSERT_HOKHAU 18, N'Na Rì, Bắc Kạn', NULL, NULL
EXEC INSERT_HOKHAU 7, N'123 Nguyễn Lương Bằng, Đống Đa, Hà Nội', NULL, NULL
EXEC INSERT_HOKHAU 13, N'456 Trần Quốc Hoàn, Cầu Giấy, Hà Nội', NULL, NULL
EXEC INSERT_HOKHAU 25, N'789 Đê La Thành, Ba Đình, Hà Nội', NULL, NULL
EXEC INSERT_HOKHAU 3, N'321 Đại Cồ Việt, Hai Bà Trưng, Hà Nội', NULL, NULL
EXEC INSERT_HOKHAU 20, N'654 Phạm Văn Đồng, Bắc Từ Liêm, Hà Nội', NULL, NULL
EXEC INSERT_HOKHAU 12, N'987 Nguyễn Trãi, Thanh Xuân, Hà Nội', NULL, NULL
EXEC INSERT_HOKHAU 21, N'135 Lê Văn Lương, Thanh Xuân, Hà Nội', NULL, NULL
EXEC INSERT_HOKHAU 9, N'246 Nguyễn Khánh Toàn, Cầu Giấy, Hà Nội', NULL, NULL
EXEC INSERT_HOKHAU 17, N'579 Trần Duy Hưng, Cầu Giấy, Hà Nội', NULL, NULL
EXEC INSERT_HOKHAU 5, N'864 Nguyễn Văn Cừ, Long Biên, Hà Nội', NULL, NULL
EXEC INSERT_HOKHAU 8, N'111 Nguyễn Chí Thanh, Đống Đa, Hà Nội', NULL, NULL
EXEC INSERT_HOKHAU 16, N'222 Trần Đại Nghĩa, Hai Bà Trưng, Hà Nội', NULL, NULL
EXEC INSERT_HOKHAU 27, N'333 Lê Trọng Tấn, Thanh Xuân, Hà Nội', NULL, NULL
EXEC INSERT_HOKHAU 6, N'444 Nguyễn Văn Linh, Long Biên, Hà Nội', NULL, NULL


------------------------------------------------------------------------
-- THEM TAM TRU

CREATE PROC INSERT_TAMTRU 
@HOTEN NVARCHAR(50), @SOCANCUOC VARCHAR(15), @NGAYSINH DATE, @GIOITINH BIT, @NOISINH NVARCHAR(200), @NGUYENQUAN NVARCHAR(200),
@DANTOC NVARCHAR(20), @TONGIAO NVARCHAR(20), @QUOCTICH NVARCHAR(20), @NOITHUONGTRU NVARCHAR(200), @NGHENGHIEP NVARCHAR(100),
@SODIENTHOAINGUOIDANGKY VARCHAR(15), @TUNGAY DATE, @DENNGAY DATE, @LYDO NVARCHAR(300)
AS
BEGIN
	INSERT INTO NHANKHAU(
		HOTEN,
		SOCANCUOC,
		NGAYSINH,
		GIOITINH,
		NOISINH,
		NGUYENQUAN,
		DANTOC,
		TONGIAO,
		QUOCTICH,
		NOITHUONGTRU,
		NGHENGHIEP,
		GHICHU)
	VALUES (@HOTEN, @SOCANCUOC, @NGAYSINH, @GIOITINH, @NOISINH, @NGUYENQUAN, @DANTOC, @TONGIAO, @QUOCTICH, @NOITHUONGTRU, @NGHENGHIEP, N'tạm trú');

	DECLARE @MANHANKHAU INT
	SELECT @MANHANKHAU = MAX(MANHANKHAU) FROM NHANKHAU;

	INSERT INTO TAMTRU(MANHANKHAU, SODIENTHOAINGUOIDANGKY, TUNGAY, DENNGAY, LYDO)
	VALUES (@MANHANKHAU, @SODIENTHOAINGUOIDANGKY, @TUNGAY, @DENNGAY, @LYDO);
END;

/*************************************************************************/

CREATE TRIGGER DELETE_TAMTRU ON TAMTRU
FOR DELETE
AS
BEGIN
	DECLARE @MANHANKHAU INT
	SELECT @MANHANKHAU = MANHANKHAU FROM deleted

	DELETE NHANKHAU
	WHERE MANHANKHAU = @MANHANKHAU
END;
-----------------------------------------------------------

EXEC INSERT_TAMTRU N'Nguyễn Văn ACE', '123456789', '1990-01-01', 1, N'Hà Nội', N'Hà Nội', N'Kinh', N'Phật giáo', N'Việt Nam', N'Hà Nội', N'Kỹ sư', '0123456789', '2022-01-01', '2023-12-31', N'Học tập';
EXEC INSERT_TAMTRU N'Trần Thị Sabo', '987654321', '1985-01-01', 0, N'Hải Phòng', N'Hải Phòng', N'Kinh', N'Phật giáo', N'Việt Nam', N'Hải Phòng', N'Giáo viên', '0987654321', '2023-02-01', '2023-11-30', N'Công tác';
EXEC INSERT_TAMTRU N'Lê Văn Dragon', '456789012', '1995-01-01', 1, N'Thái Nguyên', N'Thái Nguyên', N'Mông', N'Đạo Cao Đài', N'Việt Nam', N'Thái Nguyên', N'Y sĩ', '0123456789', '2021-03-01', '2023-10-31', N'Chăm sóc sức khỏe';
EXEC INSERT_TAMTRU N'Phạm Thị Garp', '789012345', '2000-01-01', 0, N'Hồ Chí Minh', N'Hồ Chí Minh', N'Khơ Mú', N'Tin Lành', N'Việt Nam', N'Hồ Chí Minh', N'Sinh viên', '0987654321', '2023-04-01', '2023-09-30', N'Học tập';
EXEC INSERT_TAMTRU N'Hoàng Văn Sanji', '012345678', '1988-01-01', 1, N'Hải Dương', N'Hải Dương', N'Kinh', N'Đạo Cao Đài', N'Việt Nam', N'Hải Dương', N'Nhân viên văn phòng', '0123456789', '2020-05-01', '2023-08-31', N'Làm việc';
EXEC INSERT_TAMTRU N'Trần Thị Chopper', '1234567890', '1992-01-01', 0, N'Hải Dương', N'Hải Dương', N'Kinh', N'Đạo Cao Đài', N'Việt Nam', N'Hải Dương', N'Giáo viên', '0123456789', '2020-06-01', '2023-11-30', N'Chăm sóc sức khỏe';
EXEC INSERT_TAMTRU N'Nguyễn Thị Pikachu', '0987654321', '1980-01-01', 0, N'Bắc Ninh', N'Bắc Ninh', N'Kinh', N'Phật giáo', N'Việt Nam', N'Bắc Ninh', N'Nhân viên kinh doanh', '0987654321', '2022-07-01', '2023-10-31', N'Làm việc';
EXEC INSERT_TAMTRU N'Lê Thị Hinata', '1122334455', '1987-01-01', 0, N'Hải Phòng', N'Hải Phòng', N'Kinh', N'Tin Lành', N'Việt Nam', N'Hải Phòng', N'Trình dược viên', '0123456789', '2019-08-01', '2023-09-30', N'Học tập';
EXEC INSERT_TAMTRU N'Phạm Văn Naruto', '5566778899', '1998-01-01', 1, N'Thái Bình', N'Thái Bình', N'Kinh', N'Đạo Cao Đài', N'Việt Nam', N'Thái Bình', N'Kỹ thuật viên', '0123456789', '2023-09-01', '2023-08-31', N'Làm việc';
EXEC INSERT_TAMTRU N'Uchihahaha Sasuke', '0011223344', '1982-01-01', 0, N'Hà Nội', N'Hà Nội', N'Kinh', N'Phật giáo', N'Việt Nam', N'Hà Nội', N'Bác sĩ', '0987654321', '2023-10-01', '2023-07-31', N'Chăm sóc sức khỏe';

------------------------------------------------------------------------------------

CREATE TRIGGER INSERT_TAMVANG ON TAMVANG
FOR INSERT
AS
BEGIN
	UPDATE NHANKHAU
	SET NHANKHAU.GHICHU = N'tạm vắng'
	WHERE NHANKHAU.MANHANKHAU = (SELECT MANHANKHAU FROM INSERTED);
END;

CREATE TRIGGER DELETE_TAMVANG ON TAMVANG
FOR DELETE
AS
BEGIN 
	DECLARE @MANHANKHAU INT
	SELECT @MANHANKHAU = MANHANKHAU FROM deleted

	UPDATE NHANKHAU 
	SET GHICHU = NULL
	WHERE MANHANKHAU = @MANHANKHAU

END
/**********************************************************************/

CREATE PROC INSERT_TAM_VANG
@MANHANKHAU INT, @NOITAMTRU NVARCHAR(300), @TUNGAY DATE, @DENNGAY DATE, @LYDO NVARCHAR(300)
AS
BEGIN
	
	INSERT INTO TAMVANG(
		MANHANKHAU,
		NOITAMTRU,
		TUNGAY,
		DENNGAY,
		LYDO )
	VALUES (@MANHANKHAU, @NOITAMTRU, @TUNGAY, @DENNGAY, @LYDO);
END;
------------------------------------------------------------------------

EXEC INSERT_TAM_VANG 14, N'Vinland', '2023-7-4', '2023-12-31', N'sức khỏe';
EXEC INSERT_TAM_VANG 21, N'Vinland','2023-1-31', '2023-12-31', N'học tập';
EXEC INSERT_TAM_VANG 28, N'Vinland','2022-3-23', '2023-12-31', N'abcxyz';
EXEC INSERT_TAM_VANG 9, N'Vinland','2021-1-1', '2025-12-31', N'làm việc';
EXEC INSERT_TAM_VANG 4, N'Vinland','2021-5-6', '2026-12-31', N'học tập';


----------------------------------------------------------------

CREATE TRIGGER INSERT_KHAITU ON KHAITU
FOR INSERT
AS
BEGIN
	UPDATE NHANKHAU
	SET NHANKHAU.GHICHU = N'qua đời'
	WHERE NHANKHAU.MANHANKHAU = (SELECT MANHANKHAUNGUOICHET FROM INSERTED);
END;
----------------------------------------------------------------

INSERT INTO KHAITU (
	MANHANKHAUNGUOIKHAI,
	MANHANKHAUNGUOICHET
)
VALUES (2, 3);

----------------------------------------------------------------------------

INSERT INTO THANHVIENCUAHO (
	MANHANKHAU,
	MAHOKHAU,
	QUANHEVOICHUHO
)
VALUES 
	(10, 2, N'Cụ nội'),
	(43, 2, N'Em gái'),
	(4, 1, N'Con gái nuôi'),
	(44, 1, N'Cháu rơi'),
	(29, 3, N'Mẹ kế'),
	(26, 3, N'Anh'),
	(33,12,N'Bố kế'),
	(34, 12, N'Em trai'),
	(22,17,N'sadnhan'),
	(11,17,N'MTPPPPPPPPPP');

----------------------------------------------------------------

--------------------------------------------------------
--QUẢN LÝ THU PHÍ
CREATE TABLE LOAIPHI(
	MAKHOANTHU INT IDENTITY,
	TEN NVARCHAR(50) NOT NULL,
	BATBUOC BIT NOT NULL,
	SOTIENTRENMOTNGUOI BIGINT NOT NULL,
	NGAYTAO DATE,
	MOTA NVARCHAR(300),

	PRIMARY KEY (MAKHOANTHU)
);

CREATE TABLE DONGGOP(
	MAHOKHAU INT,
	MAKHOANTHU INT,
	
	SOTIENCANDONG BIGINT,
	SOTIENDADONG BIGINT,
	TRANGTHAI BIT NOT NULL,
	NGAYDONG DATE,

	TENCHUHO NVARCHAR(300),
	DIACHI NVARCHAR(300),
	SOTHANHVIEN INT,
);


INSERT INTO LOAIPHI(TEN, BATBUOC, SOTIENTRENMOTNGUOI, MOTA)
VALUES 
	(N'Thu phí vệ sinh', 1, 120000, N'thu phí vệ sinh năm 2023'),
	(N'Đóng góp phí thương binh liệt sĩ 27/7', 1, 50000, N'thu phí đóng góp thương binh liệt sĩ 27/7, trừ mấy hộ có thương binh liệt sĩ'),
	(N'Đóng góp chiến dịch Xuân Yêu Thương', 0, 0, N'Chiến dịch Xuân Yêu Thương diễn ra tại tỉnh A, ...');

CREATE PROC INSERT_DONGGOP 
@MAHOKHAU INT, @MAKHOANTHU INT, @TRANGTHAI BIT
AS
BEGIN
	INSERT INTO DONGGOP(MAHOKHAU, MAKHOANTHU, TRANGTHAI)
	VALUES(@MAHOKHAU, @MAKHOANTHU, @TRANGTHAI);

	DECLARE @TENCHUHO NVARCHAR(300), @DIACHI NVARCHAR(300), @SOTHANHVIEN INT, @SOTIENTRENMOTNGUOI BIGINT

	SELECT @SOTIENTRENMOTNGUOI = SOTIENTRENMOTNGUOI FROM LOAIPHI 
	WHERE MAKHOANTHU = @MAKHOANTHU

	SELECT @TENCHUHO = TENCHUHO, @DIACHI = DIACHI
	FROM HOKHAU WHERE MAHOKHAU = @MAHOKHAU
	
	SELECT @SOTHANHVIEN = COUNT(MANHANKHAU) FROM THANHVIENCUAHO WHERE MAHOKHAU = @MAHOKHAU
	
	UPDATE DONGGOP
	SET TENCHUHO = @TENCHUHO, DIACHI = @DIACHI, SOTHANHVIEN = @SOTHANHVIEN, SOTIENCANDONG = @SOTIENTRENMOTNGUOI * @SOTHANHVIEN 
	WHERE MAKHOANTHU = @MAKHOANTHU AND MAHOKHAU = @MAHOKHAU;
	
	IF(@TRANGTHAI = 1) 
	BEGIN 
		UPDATE DONGGOP
		SET SOTIENDADONG = SOTIENCANDONG
		WHERE MAKHOANTHU = @MAKHOANTHU AND MAHOKHAU = @MAHOKHAU
	END
	ELSE 
	BEGIN 
	UPDATE DONGGOP
	SET SOTIENDADONG = 0
	WHERE MAKHOANTHU = @MAKHOANTHU AND MAHOKHAU = @MAHOKHAU 
	END
END;


EXEC INSERT_DONGGOP 1, 1, 1
EXEC INSERT_DONGGOP 2, 1, 0
EXEC INSERT_DONGGOP 3, 1, 1
EXEC INSERT_DONGGOP 1, 2, 1
EXEC INSERT_DONGGOP 2, 2, 0
EXEC INSERT_DONGGOP 3, 2, 0
EXEC INSERT_DONGGOP 1, 3, 0
EXEC INSERT_DONGGOP 2, 3, 1
EXEC INSERT_DONGGOP 3, 3, 0


UPDATE DONGGOP
SET	NGAYDONG = '2023-12-1'
WHERE MAHOKHAU = 1 AND MAKHOANTHU = 1;

UPDATE DONGGOP
SET NGAYDONG = '2023-11-30'
WHERE MAHOKHAU = 3 AND MAKHOANTHU = 1;

UPDATE DONGGOP
SET NGAYDONG = '2023-7-20'
WHERE MAHOKHAU = 1 AND MAKHOANTHU = 2;

UPDATE DONGGOP
SET NGAYDONG = GETDATE()
WHERE MAHOKHAU = 2 AND MAKHOANTHU = 3;










/**
 *	CREATED BY THANH
 */



