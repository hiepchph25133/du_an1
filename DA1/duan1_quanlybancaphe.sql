create database duan11
go
use duan11
go
-- dữ liệu thêm 4 cái

-- NhanVien(manv, hotennv, ngaysinh, gioitinh, diachi, sdt, macv, ngayvaolam, phanquyen, matkhau) // phanquen (admin, nhanvien)
create table NhanVien(
    -- id uniqueidentifier not null,
    manv char(10) primary key,
    hotennv nvarchar(100) not null,
    ngaysinh date not null,
    gioitinh bit not null,
    diachi nvarchar(100) not null,
    sdt varchar(10) not null,
    macv char(10) not null,
    ngayvaolam date not null,
    phanquyen nvarchar(30) not null,
    matkhau nvarchar(max) null
)
go

-- ChucVu(macv, tencv, mota)
create table ChucVu(
    macv char(10) primary key,  
    tencv nvarchar(50) not null,
    mota nvarchar(max) null
)
go

-- NhaCungCap(mancc, tenncc, diachi, sdt)
create table NhaCungCap(
    mancc char(10) primary key,
    tenncc nvarchar(max) not null,
    diachi nvarchar(200) not null,
    sdt varchar(10) not null
)
go

-- DongSP(madsp, tendsp, mota)
create table DongSP(
    madsp char(10) primary key,
    tendsp nvarchar(100) not null,
    mota nvarchar(max) null
)
go

-- SanPham(masp, tensp, madsp, mancc, hinhanh, giasp)
create table SanPham(
    masp char(10) primary key,
    tensp nvarchar(100) not null,
    mancc char(10) not null,
    madsp char(10) not null,
    hinhanh nvarchar(50) null,
    giasp int not null
)
go

-- CaLamViec(maclv, tenclv, giobatdau, gioketthuc, sotien(thu trong ca đó))
create table CaLamViec(
    maclv char(10) primary key,
    tenclv nvarchar(100) not null,
    giobatdau time(7) not null,
    gioketthuc time(7) not null,
    sotienthutrongca int not null
)
go

-- Ban(maban, makv, tenban, thuoctinh(bàn trống hay không))
create table Ban(
    maban char(10) primary key,
    makv char(10) not null,
    tenban nvarchar(100) not null,
    thuoctinh int not null    
)
go

-- HoaDon(mahd, manv, makh, maban, ngaythanhthoanhd, tongtien, chiphikhac)
create table HoaDon(
    mahd char(10) primary key,
    manv char(10) not null,
    makh char(10) not null,
    maban char(10) not null,
    ngaythanhtoanhd date not null,
    tongtien int not null,
    chiphikhac int not null
)
go

-- -> ChiTietHoaDon(mahd, masp, soluong)
create table ChiTietHoaDon(
    mahd char(10) primary key,
    masp char(10) not null,
    soluong int not null
)
go

-- KhuVuc(makv, tenkv, trangthai)
create table KhuVuc(
    makv char(10) primary key,
    tenkv nvarchar(100) not null,
    trangthai int not null
)
go

-- HoaDonNhapHang(mahdnh, manv, ngaynhaphang)
create table HoaDonNhapHang(
    mahdnh char(10) primary key,
    manv char(10) not null,
    ngaynhaphang date not null,
    foreign key (manv) references NhanVien(manv)
)
go
-- -> ChiTietNhapHang(manh, mahdnh, soluong, dongia, thanhtien)
create table ChiTietNhapHang(
    manh char(10) primary key,
    mahdnh char(10) not null,
    soluong int not null,
    dongia int not null,
    thanhtien int not null
)
go

-- -> ChiTietLuongNhanVien(manv, maclv, tongsocalamviectrongthang, Luong)
create table ChiTietLuongNhanVien(
    manv char(10) not null,
    maclv char(10) not null,
    tongsocalamviec int not null,
    luong int not null
    constraint PK_ChiTietLuongNhanVien primary key (manv, maclv),
    foreign key (manv) references NhanVien(manv)
)
go

-- KhachHang(makh, hotenkh, gioitinh, diachi, sdt)
create table KhachHang(
    makh char(10) primary key,
    hotenkh nvarchar(100) not null,
    gioitinh bit not null,
    diachi nvarchar(200) not null,
    sdt varchar(10) not null,
)
go

-- Ban liên kết với KhuVuc
alter table Ban add constraint PK_KhuVuc_makv foreign key (makv) references KhuVuc(makv)
go

-- Ban liên kết với HoaDon
alter table HoaDon add constraint PK_Ban_maban foreign key (maban) references Ban(maban)
go

-- HoaDon liên kết với KhachHang
alter table HoaDon add constraint PK_KhachHang_makh foreign key (makh) references KhachHang(makh)
go

-- SanPham liên kết với NhaCungCap
alter table SanPham add constraint PK_NhaCungCap_mancc foreign key (mancc) references NhaCungCap(mancc)
go

-- NhanVien liên kết với ChucVu
alter table NhanVien add constraint PK_ChucVu_macv foreign key (macv) references ChucVu(macv)
go

-- SanPham liên kết với DongSP
alter table SanPham add constraint PK_DongSP_madsp foreign key (madsp) references DongSP(madsp)
go

--  ChiTietLuongNhanVien liên kết với CaLamViec
alter table ChiTietLuongNhanVien add constraint PK_CaLamViec_maclv foreign key (maclv) references CaLamViec(maclv)
go

-- ChiTietLuongNhanVien liên kết với NhanVien
-- alter table NhanVien add constraint PK_ChiTietLuongNhanVien_manv foreign key (manv) references ChiTietLuongNhanVien(manv)
-- go-- Lỗi 
-- Buộc Thêm Thuộc Tính Lương Vào NhanVien

-- HoaDon liên kết với NhanVien
alter table HoaDon add constraint PK_NhanVien_manv foreign key (manv) references NhanVien(manv)
go

-- HoaDonNhapHang liên kết với ChiTietNhapHang
alter table ChiTietNhapHang add constraint PK_HoaDonNhapHang_mahdnh foreign key (mahdnh) references HoaDonNhapHang(mahdnh)
go

-- ChiTietHoaDon liên kết với HoaDon
alter table ChiTietHoaDon add constraint PK_HoaDon_mahd foreign key (mahd) references HoaDon(mahd)
go

-- ChiTietHoaDon liên kết với SanPham
alter table ChiTietHoaDon add constraint PK_SanPham_masp foreign key (masp) references SanPham(masp)
go
