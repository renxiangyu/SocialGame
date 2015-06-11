package game.pack.db;
public class RoleInfo {
	
		public String roleName;
		public String roleHeadAddr;
		public int roleBodyId;
		public int rolePlayTime;
		public int roleLevel;
		
		
		
		public void setRoleName(String roleName)
		{
			this.roleName=roleName;
		}     //s1
		public void setRoleHeadAddr(String roleHeadAddr){
			this.roleHeadAddr=roleHeadAddr;
		}      //s2
		public void setRoleBodyId(int roleBodyId){
			this.roleBodyId=roleBodyId;
		}    //s3
		public void setRolePlayTime(int rolePlayTime){
			this.rolePlayTime=rolePlayTime;
		}    //s4
		public void setRoleLevel(int roleLevel){
			this.roleLevel=roleLevel;
		}   //s5
		
		public String getRoleName(){
			return this.roleName;
		}   //g1
		public String getRoleHeadAddr(){
			return this.roleHeadAddr;
		}    //g2
		public int getRoleBodyId(){
			return this.roleBodyId;
		}       //g3
		public int getRolePlayTime(){
			return this.rolePlayTime;
		}      //g4
		public int getRoleLevel(){
			return this.roleLevel;
		}  //g5
		
		
	}
	