/**
 *
 * @include "../../../resources/evan/form/form.js"
 */

$(function(){
	var form = new Form({
			
		}, {
			ormType:{required:true},
			dbUrl:{required:true},
			dbPort:{required:true},
			dbSid:{required:true},
			dbUser:{required:true},
			dbPassword:{required:true},
			packageNamePo:{required:true},
			packageNameDao:{required:true},
			basePoName:{required:true},
			baseDaoName:{required:true}
		}, {
			ormType:{required:'orm type 蹇呴』杈撳叆'},
			dbUrl:{required:'db url 蹇呴』杈撳叆'},
			dbPort:{required:'db port 蹇呴』杈撳叆'},
			dbSid:{required:'db sid 蹇呴』杈撳叆'},
			dbUser:{required:'db user 蹇呴』杈撳叆'},
			dbPassword:{required:'db password 蹇呴』杈撳叆'},
			packageNamePo:{required:'po package name 蹇呴』杈撳叆'},
			packageNameDao:{required:'dao package name 蹇呴』杈撳叆'},
			basePoName:{required:'basePo name 蹇呴』杈撳叆'},
			baseDaoName:{required:'baseDao name 蹇呴』杈撳叆'}
		}
	);	
})