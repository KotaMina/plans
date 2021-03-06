package jp.co.plans.apps.domain.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.plans.apps.domain.criteria.UserCriteria;
import jp.co.plans.apps.domain.service.user.UserService;
import jp.co.plans.apps.domain.service.user.component.CheckAuthorityModule;
import jp.co.plans.apps.domain.service.user.component.GetRelationIdModule;
import jp.co.plans.apps.domain.service.user.component.InsertUserModule;
import jp.co.plans.apps.domain.service.user.component.LoginUserModule;
import jp.co.plans.apps.domain.service.user.component.ResetUserInfoModule;
import jp.co.plans.apps.domain.service.user.component.SearchUserModule;
import jp.co.plans.apps.domain.service.user.component.UpdateUserModule;

@Service
public class UserServiceImpl implements UserService {

	/**ログインモジュール*/
	@Autowired
	private LoginUserModule loginModule;

	/**参照モジュール*/
	@Autowired
	private SearchUserModule searchModule;

	/**登録モジュール*/
	@Autowired
	private InsertUserModule insertModule;

	/**登録モジュール*/
	@Autowired
	private UpdateUserModule updateModule;

	/**リセットモジュール*/
	@Autowired
	private ResetUserInfoModule resetModule;

	@Autowired
	private CheckAuthorityModule checkAuthorityModule;

	@Autowired
	private GetRelationIdModule getRelationIdModule;

	/**
	 * ログインする。
	 * @param critera
	 * @return
	 */
	@Override
	public String login(UserCriteria criteria) {
		//ログインを行う。
		return loginModule.execute(criteria);
	}

	/**
	 * ユーザー情報を参照する。
	 * @param critera
	 * @return
	 */
	@Override
	public int search(UserCriteria criteria) {
		//参照結果を返却する。
		return searchModule.execute(criteria);
	}

	/**
	 * 新規ユーザーを登録する。
	 */
	@Override
	public int insert(UserCriteria criteria) {
		//登録を行う。
		return insertModule.execute(criteria);
	}

	/**
	 * ユーザー情報を更新する。
	 * @param criteria
	 * @return
	 */
	@Override
	public int update(UserCriteria criteria) {
		//更新を行う。
		return updateModule.execute(criteria);
	}

	/**
	 * ログイン失敗回数をリセットする。
	 * @param criteria
	 * @return
	 */
	@Override
	public int resetFailedCount(UserCriteria criteria) {
		//カウントを０にする。
		return resetModule.resetCount(criteria);
	}

	/**
	 * 権限チェックを行う。
	 * @param userId
	 * @param authority
	 */
	@Override
	public void checkAuthority(String userId, String authority) {
		checkAuthorityModule.execute(userId, authority);
	}

	/**
	 * リレーションIDを取得する。
	 * 取得できない場合は、例外を発生する。
	 * @param userId
	 * @return
	 */
	@Override
	public String getRelationId(String userId) {
		return getRelationIdModule.execute(userId);
	}
}
