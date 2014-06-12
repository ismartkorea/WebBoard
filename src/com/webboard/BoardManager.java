package com.webboard;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
/**]
 * 
 * @author jeong
 *
 */
public class BoardManager {
	
	Logger logger = Logger.getLogger( this.getClass() );

	private static BoardManager instance = new BoardManager();
	
	public static BoardManager getInstance() {
			return instance;
	}
	
	private BoardManager() {
	}
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		DataSource ds = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/webboard");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return ds.getConnection();
	}
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int getListCount() throws BoardManagerException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("SELECT count(*) FROM board01");
			
			rs = pstmt.executeQuery();
			//
			int count = 0;
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			return count;
		} catch(SQLException ex) {
			throw new BoardManagerException("getListCount",ex);
		} finally {
			if(rs != null) try { rs.close(); } catch(SQLException ex) {}
			if(pstmt != null) try {pstmt.close(); } catch(SQLException ex) {}
			if(conn != null) try {conn.close(); } catch(SQLException ex) {}
		}
		
	}
	

	/**
	 * 리스트 조회 처리.
	 * @param startRow
	 * @param endRow
	 * @return
	 * @throws BoardManagerException
	 */
	public ArrayList<BoardVo> getBoardList(int startRow, int endRow)  throws BoardManagerException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT no, title, reg_name, reg_date, upd_name, upd_date");
			sb.append("  FROM board01");
			sb.append(" ORDER BY reg_date DESC");
			sb.append(" LIMIT ?, ?");
			
			pstmt = conn.prepareStatement(sb.toString());
		    pstmt.setInt(1, startRow);
		    pstmt.setInt(2, endRow - startRow + 1);
			
			rs = pstmt.executeQuery();
			
			ArrayList<BoardVo> resultList = new ArrayList<BoardVo>(endRow - startRow +1);
			
			if(rs.next()) {
				
				do {
					BoardVo board = new BoardVo();
					board.setNo(rs.getInt("no"));
					board.setTitle(rs.getString("title"));
					board.setRegName(rs.getString("reg_name"));
					board.setRegDate(rs.getTimestamp("reg_date"));
					board.setUpdName(rs.getString("upd_name"));
					board.setUpdDate(rs.getTimestamp("upd_date"));
					
					resultList.add(board);
				} while (rs.next());

			
				return resultList;
			} else {
				return resultList;
			}
			
		} catch(SQLException ex) {
			throw new BoardManagerException("getBoardList",ex);
		} finally {
			if (rs !=null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	/**
	 * Select 조회.
	 * @param no
	 * @return
	 * @throws BoardManagerException
	 */
	public BoardVo selectBoard(int no) throws BoardManagerException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement(
					"SELECT no, title, content, reg_name, reg_date, upd_date"
				    + " FROM board01 WHERE no = ?");
			
			pstmt.setInt(1,  no);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
				BoardVo board = new BoardVo();
				
				board.setNo(rs.getInt("no"));
				board.setTitle(rs.getString("title"));
				
				// Content 파라미터 취득.
				Reader reader = null;
				try {
					reader = rs.getCharacterStream("content");
					char[] buff = new char[1024];
					int len = -1;
					StringBuffer sb = new StringBuffer(1024);
					while((len = reader.read(buff)) != -1) {
						sb.append(buff, 0, len);
					}
					board.setContent(sb.toString());
				} catch(IOException ioe) {
					throw new BoardManagerException("boardInsert",ioe); 
				} finally {
					if (reader != null)
						try {
							reader.close();
						} catch(IOException ioe) {}
				}				
				
				//board.setContent(rs.getString("content"));
				board.setRegName(rs.getString("reg_name"));
				board.setRegDate(rs.getTimestamp("reg_date"));
				board.setUpdDate(rs.getTimestamp("upd_date"));

				return board;
			} else {
				return null;
			}
		} catch(SQLException ex) {
			throw new BoardManagerException("selectBoard",ex);
		} finally {
			if(rs != null) try { rs.close(); } catch(SQLException ex) {}
			if(pstmt != null) try {pstmt.close(); } catch(SQLException ex) {}
			if(conn != null) try {conn.close(); } catch(SQLException ex) {}
		}
	}
	
	/**
	 * 저장 처리기능.
	 * @param admin
	 * @throws AdminManagerException
	 */
	public void insertBoard(BoardVo board) throws BoardManagerException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();		
			
			pstmt = conn.prepareStatement(
					"INSERT INTO board01(title, content, reg_name, reg_date, upd_name, upd_date)" + 
					" VALUES (?, ?, ?, now(), ?, now())");
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getRegName());
			pstmt.setString(4, board.getRegName());		
			
			pstmt.executeUpdate();
			
		} catch(SQLException ex) {
			throw new BoardManagerException("insertBoard",ex);	
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
	}	
	/**
	 * 수정처리 기능.
	 * @param admin
	 * @throws AdminManagerException
	 */
	public void updateBoard(BoardVo board) throws BoardManagerException {
		
		logger.debug("board vo : " + board.toString());
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();			
			
			pstmt = conn.prepareStatement(
					"UPDATE board01"
					 + " SET title = ?,"
					 + " content = ?,"
					 + " upd_name =?,"
					 + " upd_date = now()"
					 + " WHERE no = ?");
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getUpdName());
			pstmt.setInt(4, board.getNo());
			
		    int result = pstmt.executeUpdate();
		    if (result == 0) {
		    	throw new BoardManagerException("존재하지 않는 NO");
		    }
		    
		} catch(SQLException ex) {
			throw new BoardManagerException("updateBoard",ex);			
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}	
	/**
	 * 삭제처리기능.
	 * @param id
	 * @return
	 * @throws AdminManagerException
	 */
	public int deleteBoard(int no) throws BoardManagerException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement(
					"DELETE FROM board01 WHERE no = ?");
			pstmt.setInt(1,  no);
			
			int result = pstmt.executeUpdate();
			if (result == 0) {
				throw new BoardManagerException("존재하지 않는 NO");
			}
			
			return result;
			
		} catch(SQLException ex) {
			throw new BoardManagerException("deleteBoard",ex);			
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
	
	
}
