create database duan1_xay_dung_ung_dung_ban_kinh_thoi_trang_FourGlasses_1
go
use duan1_xay_dung_ung_dung_ban_kinh_thoi_trang_FourGlasses_1
go
--Tạo Bảng Login
create table Login(
    UserName varchar(max) not null,
    Passworld nvarchar(max) not null,
    Role nvarchar(10) null
)
go

--Tạo Bảng NhanVien
create table NhanVien(
    MaNV varchar(10) primary key,
    HoTenNV nvarchar(200) not null,
    NgaySinh date not null,
    GioiTinh bit not null,
    Sdt varchar(10) not null,
    MaCV varchar(10) not null,
	diachi nvarchar(50)  not null,
    TrangThai int not null,
    MatKhau nvarchar(50) null,
)
go
Select * from NhanVien
--Xóa Bảng Nhân Viên


--Tạo Bảng ChucVu
create table ChucVu(
    MaCV varchar(10) primary key,
    TenCV nvarchar(100) not null,
    MoTa nvarchar(max) null
)
go

--Tạo Bảng KhachHang
create table KhachHang(
    MaKH varchar(10) primary key,
    HoTenKH nvarchar(200) not null,
    NgaySinh date not null,
    DiaChi nvarchar(200) not null,
    Sdt varchar(10) not null,
    ThanhPho nvarchar(100) not null,
    QuocGia nvarchar(50) not null
)
go
Select * from KhachHang
--Tạo Bảng HoaDon
create table HoaDon(
    MaHD varchar(10) primary key,
    MaNV varchar(10) not null,
    MaKH varchar(10) not null,
    NgayTao date not null,
    TinhTrang int not null,
    TenNguoiNhan nvarchar(50) not null,
    DiaChi nvarchar(100) not null,
    Sdt varchar(10) not null
)
go

--Tạo Bảng ChiTietHoaDon
create table ChiTietHoaDon(
    MaHD varchar(10) not null,
    MaSP varchar(10) not null,
    SoLuong int not null,
    constraint PK_ChiTietHoaDon primary key (MaHD, MaSP)
)
go

--Tạo Bảng DongSP
create table DongSP(
    MaDSP varchar(10) primary key,
    TenDSP nvarchar(100) not null,
    HinhAnh nvarchar(50) not null
)
go

--Tạo Bảng MauSac
create table MauSac(
    MaMS varchar(10) primary key,
    TenMS nvarchar(100) not null
)
go
-- Tạo Bảng Hang
create table hang(
	MaHang varchar(10) primary key not null,
	tenhang nvarchar(100) not null
)
--Tạo Bảng SanPham
create table SanPham(
    MaSP varchar(10) primary key,
    TenSP nvarchar(200) not null,
    MaMS varchar(10) not null,
    MaDSP varchar(10) not null,
	MaHang varchar(10) not null,
    MoTa nvarchar(100) null,
    SoLuong int not null,
    GiaNhap int not null,
    GiaBan int not null,
    HinhAnh nvarchar(50) not null
)
go

-- NhanVien liên kết với ChucVu
alter table NhanVien add constraint PK_NhanVien_MaCV foreign key (MaCV) references ChucVu(MaCV)
go

-- HoaDon liên kết với NhanVien
alter table HoaDon add constraint PK_HoaDon_MaNV foreign key (MaNV) references NhanVien(MaNV)
go

-- HoaDon liên kết với KhachHang
alter table HoaDon add constraint PK_HoaDon_MaKH foreign key (MaKH) references KhachHang(MaKH)
go

-- HoaDonChiTiet liên kết với HoaDon
alter table CHiTIetHoaDon add constraint PK_ChiTietHoaDon_MaHD foreign key (MaHD) references HoaDon(MaHD)
go

-- HoaDonChiTiet liên kết với SanPham
alter table ChiTietHoaDon add constraint PK_ChiTietHoaDon_MaSP foreign key (MaSP) references SanPham(MaSP)
go
-- Hang liên kết với SanPham
alter table SanPham add constraint PK_SanPham_MaHang foreign key (MaHang) references hang(MaHang)
go
-- SanPham liên kết với DongSP
alter table SanPham add constraint PK_SanPham_MaDSP foreign key (MaDSP) references DongSP(MaDSP)
go

-- SanPham liên kết với MauSac
alter table SanPham add constraint PK_SanPham_MaMS foreign key (MaMS) references MauSac(MaMS)
go
