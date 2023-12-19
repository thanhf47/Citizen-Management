package com.example.citizenmanagement.models;

import javafx.scene.control.Dialog;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseConnection {
    private Connection connection;

    public DatabaseConnection() {
        String dbName = "QUANLYDANCU";
        String dbUser = "sa";
        String dbPassword = "123456789";
        String url = "jdbc:sqlserver://DESKTOP-0686QHH:1433;databaseName=" + dbName + ";IntegratedSecurity=false;encrypt=false;trustSeverCertificate=true;";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Citizen Manager Section - Phần Đăng Nhập
    public ResultSet getCitizenManagerData(String tenDangNhap, String matKhau) {

        String query = "SELECT * FROM NGUOIQUANLY\n" +
                "WHERE TENDANGNHAP = '" + tenDangNhap + "' AND MATKHAU = '" + matKhau +"'";
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public ResultSet checkCitizenManagerUsernameExisted(String tenDangNhap) {
        String query = "SELECT * FROM NGUOIQUANLY\n" +
                "WHERE TENDANGNHAP = '" + tenDangNhap + "'";
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public ResultSet checkCitizenManagerAccountExisted(String hoTen, String tenDangNhap, String soDienThoai, int vaiTro) {
        String query = "SELECT * FROM NGUOIQUANLY\n" +
                "WHERE HOTEN = N'" + hoTen + "' AND TENDANGNHAP = '" + tenDangNhap + "' AND SODIENTHOAI = '" + soDienThoai + "' AND VAITRO = '" + vaiTro + "'";
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }
    public void updateCitizenManagerAccountPassword(String hoTen, String tenDangNhap, String soDienThoai, int vaiTro, String maKhau) {
        String query = "UPDATE NGUOIQUANLY SET MATKHAU = '" + maKhau + "' \n" +
                "WHERE HOTEN = N'" + hoTen+ "' AND TENDANGNHAP = '" + tenDangNhap + "' AND SODIENTHOAI = '" + soDienThoai + "' AND VAITRO = '" + vaiTro + "'";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setCitizenManagerData(String hoTen, String tenDangNhap, String matKhau, String soDienThoai, int vaiTro) {
        String query = "INSERT INTO NGUOIQUANLY(HOTEN, TENDANGNHAP, MATKHAU, SODIENTHOAI, VAITRO)\n" +
                        "VALUES (N'" + hoTen + "', '" + tenDangNhap + "', '" + matKhau + "', '" + soDienThoai + "', "+ Integer.toString(vaiTro)+ ")";

        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ResultSet getNumberOfNhanhKhau() {
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) from NHANKHAU");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getNumberOfHoKhau(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MAHOKHAU) from HOKHAU");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getNumberOfTamTru(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMTRU) FROM TAMTRU WHERE YEAR(TUNGAY) = YEAR(GETDATE()) ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public ResultSet getNumberOfTamVang(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMVANG) FROM TAMVANG WHERE YEAR(TUNGAY) = YEAR(GETDATE())");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getNumberOfNhanKhauNam(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) from NHANKHAU where GIOITINH = 1");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public ResultSet getNumberOfNhanKhauNu(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) from NHANKHAU where GIOITINH = 0");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getNumberOfNhanKhauDuoi3Tuoi(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) \n" +
                    "from NHANKHAU\n" +
                    "where YEAR(GETDATE()) - NAMSINH < 3 AND YEAR(GETDATE()) - NAMSINH >= 0");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public ResultSet getNumberOfNhanKhauTu3Den10Tuoi(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) \n" +
                    "from NHANKHAU\n" +
                    "where YEAR(GETDATE()) - NAMSINH >= 3 AND YEAR(GETDATE()) - NAMSINH < 10");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getNumberOfNhanKhauTu10Den18Tuoi(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) \n" +
                    "from NHANKHAU\n" +
                    "where YEAR(GETDATE()) - NAMSINH >= 10 AND YEAR(GETDATE()) - NAMSINH < 18");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getNumberOfNhanKhauTu18Den60Tuoi(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) \n" +
                    "from NHANKHAU\n" +
                    "where YEAR(GETDATE()) - NAMSINH >= 18 AND YEAR(GETDATE()) - NAMSINH < 60");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public ResultSet getNumberOfNhanKhauTren60Tuoi(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) \n" +
                    "from NHANKHAU\n" +
                    "where YEAR(GETDATE()) - NAMSINH >= 60");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public ResultSet getNamHienTai(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select YEAR(GETDATE())");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public ResultSet getHoKhauOfNamHienTai(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAHOKHAU)\n" +
                    "FROM HOKHAU");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getHoKhauOfNam(int r){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAHOKHAU)\n" +
                    "FROM HOKHAU\n" +
                    "WHERE YEAR(NGAYTAO) =" + r);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getTamTruOfThangVaNam(int thang,int nam){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMTRU)\n" +
                    "FROM TAMTRU\n" +
                    "WHERE MONTH(TUNGAY) =" + thang +"\n" +
                    "AND YEAR(TUNGAY) =" + nam);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;

    }

    public ResultSet getTamTruViLyDoHocTap(int nam){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMTRU)\n" +
                    "FROM TAMTRU\n" +
                    "WHERE LYDO LIKE N'%Học tập%' AND YEAR(TUNGAY)= " + nam);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  rs;
    }
    public ResultSet getTamTruViLyDoLamViec(int nam){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMTRU)\n" +
                    "FROM TAMTRU\n" +
                    "WHERE LYDO LIKE N'%Làm việc%' AND YEAR(TUNGAY)=" + nam);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  rs;
    }

    public ResultSet getTamTruViLyDoSucKhoe(int nam){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMTRU)\n" +
                    "FROM TAMTRU\n" +
                    "WHERE LYDO LIKE N'%Chăm sóc sức khỏe%' AND YEAR(TUNGAY)=" + nam);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  rs;
    }

    public ResultSet getTamVangOfThangVaNam(int thang,int nam){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMVANG)\n" +
                    "FROM TAMVANG\n" +
                    "WHERE MONTH(TUNGAY) =" +thang + "\n" +
                    "AND YEAR(TUNGAY) ="+ nam);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getTamVangViLyDoHocTap(int nam){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMVANG) FROM TAMVANG   WHERE LYDO LIKE N'%Học tập%' AND YEAR(TUNGAY) =" +nam);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  rs;
    }

    public ResultSet getTamVangViLyDoLamViec(int nam){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMVANG) FROM TAMVANG   WHERE LYDO LIKE N'%Làm việc%' AND YEAR(TUNGAY) =" + nam);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  rs;
    }

    public ResultSet getTamVangViLyDoSucKhoe(int nam){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMVANG) FROM TAMVANG   WHERE LYDO LIKE N'%sức Khoẻ%' AND YEAR(TUNGAY) =" + nam);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  rs;
    }

    // ho khau
    public int addHoKhau(String ma_ch, String ngaythem, String diachi, String ghichu){
        if(!ma_ch.isEmpty() && !diachi.isEmpty() && !ngaythem.isEmpty()) {
            String query = "insert into HOKHAU (IDCHUHO, DIACHI, NGAYTAO, GHICHU) VALUES (?, ?, ?, ?)";
            try {
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setString(1, ma_ch);
                statement.setString(2, diachi);
                statement.setString(3, ngaythem);
                if(ghichu.isEmpty())
                    statement.setString(4,null);
                else
                    statement.setString(4, ghichu);

                statement.executeUpdate();
                return 1;
            } catch (Exception e) {
                return 0;
            }
        }
        else
            return 0;
    }
    public ResultSet getDanhSachHoKhau(){
        String query = "select * from HOKHAU";
        ResultSet resultSet=null;
        try {
             resultSet = connection.createStatement().executeQuery(query);
        }catch (Exception e){
        }
        return resultSet;
    }
    public ResultSet timKiem(String dieukien){
        ResultSet resultSet=null;
        String query = "SELECT * FROM HOKHAU\n" +
                "WHERE MAHOKHAU LIKE ? OR IDCHUHO LIKE ? OR DIACHI LIKE ? OR NGAYTAO LIKE ? OR GHICHU LIKE ?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + dieukien + "%");
            statement.setString(2, "%" + dieukien + "%");
            statement.setString(3, "%" + dieukien + "%");
            statement.setString(4, "%" + dieukien + "%");
            statement.setString(5, "%" + dieukien + "%");
            resultSet = statement.executeQuery();
        }catch (Exception e){
            System.out.println("timkiem");
        }
        return resultSet;
    }
    public int capNhatHoKhau(String idHoKhau, String maChuHo, String diaChi, String ghiChu){
        ResultSet resultSet=null;
        try {
            String capnhat = "update HOKHAU set IDCHUHO=?, DIACHI=?, GHICHU=? where MAHOKHAU=?";
            PreparedStatement preparedStatement = connection.prepareStatement(capnhat);
            preparedStatement.setString(1,maChuHo);
            preparedStatement.setString(2,diaChi);

            if(ghiChu.isEmpty())
                preparedStatement.setString(3,null);
            else
                preparedStatement.setString(3, ghiChu);
            preparedStatement.setString(4,idHoKhau);
            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println("loi o capnhat !!!!!!!!!!!!!");
            return 0;
        }
    }

    public boolean dangKiTamVang(int maNhanKhau,String tuNgay, String denNgay,String lyDo, String noiTamTru){
        ResultSet resultSet = null;
        String dangkitamvang = "EXEC INSERT_TAM_VANG ?, ?, ?, ?,?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(dangkitamvang);
            preparedStatement.setInt(1,maNhanKhau);

            preparedStatement.setString(2,tuNgay);

            preparedStatement.setString(3,denNgay);

            preparedStatement.setString(4,lyDo);

            if(noiTamTru.isEmpty())
                preparedStatement.setString(5,null);
            else
                preparedStatement.setString(5,noiTamTru);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("loi o dang ki tam vang");
            return false;
        }
    }


    public ResultSet getNumberOfTamTru(int nam) {
        ResultSet resultSet = null;
        String query = "SELECT COUNT(MAGIAYTAMTRU) FROM TAMTRU WHERE YEAR(TUNGAY) =" + nam;
        Statement st;
        try {
            st = connection.createStatement();
            resultSet = st.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public ResultSet getDanhSachTamVang() {
        ResultSet resultSet = null;
        String query= "SELECT * FROM TAMVANG TV , NHANKHAU NK WHERE TV.MANHANKHAU = NK.MANHANKHAU";
        Statement statement;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;

    }

    public ResultSet timKiemTamVang(String timkiem){
        ResultSet resultSet = null;
        String query = "WHERE MAGIAYTAMVANG LIKE ? OR MANHANKHAU LIKE ? OR NOITAMTRU LIKE ? OR TUNGAY = ? OR DENNGAY = ? OR LYDO LIKE ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,"%" + timkiem + "%" );
            statement.setString(2,"%" + timkiem + "%" );
            statement.setString(3,"%" + timkiem + "%");
            statement.setString(4,"%" + timkiem + "%");
            statement.setString(5,"%" + timkiem + "%");
            statement.setString(6,"%" + timkiem + "%");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;

    }

    public ResultSet KiemTraXemMaNhanKhauDaTonTaiTrongTamVang(int manhankhau){

        ResultSet resultSet = null;
        String query = "SELECT COUNT(MANHANKHAU) FROM TAMVANG WHERE MANHANKHAU =" + manhankhau;

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;

    }

    public ResultSet KiemTraMaNhanKhauCoTonTaiHayKhong(int manhankhau){
        ResultSet resultSet = null;
        String query = "SELECT COUNT(MANHANKHAU) FROM NHANKHAU WHERE MANHANKHAU =" + manhankhau;

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }



    /***************************************************************************/
    // Quản lý thu phí
    public ResultSet getDanhSachDongPhi() {
        String query = "SELECT HK.MAHOKHAU, NK.HOTEN, HK.DIACHI, COUNT(TV.MANHANKHAU)\n" +
                "FROM HOKHAU HK INNER JOIN NHANKHAU NK ON HK.IDCHUHO = NK.MANHANKHAU\n" +
                "\tINNER JOIN THANHVIENCUAHO TV ON HK.MAHOKHAU = TV.MAHOKHAU\n" +
                "GROUP BY HK.MAHOKHAU, NK.HOTEN, HK.DIACHI";
        Statement statement;
        ResultSet resultSet=null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
        }
        return resultSet;
    }

    public ResultSet danhsachdongphi_timKiem(String condition) {
        String query = "SELECT HK.MAHOKHAU\n" +
                "FROM HOKHAU HK INNER JOIN NHANKHAU NK ON HK.IDCHUHO = NK.MANHANKHAU\n" +
                "INNER JOIN THANHVIENCUAHO TV ON HK.MAHOKHAU = TV.MAHOKHAU\n" +
                "WHERE HK.MAHOKHAU LIKE '%" + condition + "%' \n" +
                "\tOR NK.HOTEN LIKE N'%" + condition + "%'\n" +
                "\tOR DIACHI LIKE N'%" + condition+ "%'\n" +
                "GROUP BY HK.MAHOKHAU, NK.HOTEN, HK.DIACHI";
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }


    public ResultSet getNumberOfTamVang(int nam){
        ResultSet resultSet = null;
        String query = "SELECT COUNT(MAGIAYTAMVANG) FROM TAMVANG WHERE YEAR(TUNGAY) <= " + nam;
        Statement st;
        try {
            st = connection.createStatement();
            resultSet = st.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }


    public void themKhoanThuPhi(String tenKhoanThu, int batBuoc, int soTienCanDong, LocalDate ngayTao, String moTa) {
        String query = "INSERT INTO LOAIPHI(TEN, BATBUOC, SOTIENTRENMOTNGUOI, NGAYTAO, MOTA)\n" +
                "VALUES (N'" + tenKhoanThu + "', " + batBuoc + ", "+ soTienCanDong + ", '" + ngayTao.toString() + "', N'" + moTa +"')";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int layMaKhoanThu(String tenKhoanThu,int batBuoc, int soTienCanDong, LocalDate ngayTao, String moTa) {
        int maKhoanThu = -1;

        String query = "SELECT MAKHOANTHU\n" +
                "FROM LOAIPHI\n" +
                "WHERE TEN = N'" + tenKhoanThu + "' AND BATBUOC = " + batBuoc +
                " AND SOTIENTRENMOTNGUOI = " + soTienCanDong + "AND NGAYTAO = '" + ngayTao.toString() + "' AND MOTA = N'" + moTa +"'";
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.isBeforeFirst()){
                resultSet.next();
                maKhoanThu = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return maKhoanThu;
    }
    public void themDanhSachThuPhi(int maHoKhau, int maKhoanThu, int soTienCanDong, int trangThai) {
        String query = "INSERT INTO DONGGOP(MAHOKHAU, MAKHOANTHU, SOTIENCANDONG, TRANGTHAI)\n" +
                "VALUES (" + maHoKhau + ", " + maKhoanThu + ", " + soTienCanDong + ", " + trangThai + ")";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int xoaHoKhau(String maHoKhau) {
        int res = 0;
        String query = "DELETE HOKHAU\n" +
                "WHERE MAHOKHAU = " + maHoKhau;
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            res = 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public ResultSet getTongSoTienDaThuPhi(){
        ResultSet resultSet = null;
        Statement statement;
        String query = "SELECT SUM(SOTIENCANDONG) FROM DONGGOP WHERE TRANGTHAI = 1";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public ResultSet getNumberOfCacLoaiPhi(){
        ResultSet resultSet = null;
        Statement statement;
        String query = "SELECT COUNT(MAKHOANTHU) FROM LOAIPHI";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

}


