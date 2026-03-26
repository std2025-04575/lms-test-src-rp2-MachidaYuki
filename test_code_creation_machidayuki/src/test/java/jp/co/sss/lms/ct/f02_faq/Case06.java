package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;

/**
 * 結合テスト よくある質問機能
 * ケース06
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース06 カテゴリ検索 正常系")
public class Case06 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		goTo("http://localhost:8080/lms/");
		pageLoadTimeout(10);
		assertEquals("ログイン | LMS", getTitle());
		getEvidence(new Object(){});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		inputAtElement(getElementById("loginId"), "StudentAA01");
		inputAtElement(getElementById("password"), "StudentAA01Test");
		clickElement(getElementByCssSelector("input[class='btn btn-primary']"));
		visibilityTimeout(By.cssSelector("li[class='active']"), 10);
		assertEquals("コース詳細 | LMS", getTitle());
		getEvidence(new Object(){});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		clickElement(getLink("機能"));
		clickElement(getLink("ヘルプ"));
		visibilityTimeout(By.cssSelector("div[class='panel panel-primary']"), 10);
		assertEquals("ヘルプ | LMS", getTitle());
		getEvidence(new Object(){});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		clickElement(getLink("よくある質問"));
		changeWindow();
		visibilityTimeout(By.cssSelector("h2"), 10);
		assertEquals("よくある質問 | LMS", getTitle());
		getEvidence(new Object(){});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 カテゴリ検索で該当カテゴリの検索結果だけ表示")
	void test05() {
		clickElement(getLink("【研修関係】"));
		scrollBy("window.innerHeight");
		assertEquals("Q.キャンセル料・途中退校について", getText(getElementByCssSelector("dt[class='mb10']")));
		getEvidence(new Object(){});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 検索結果の質問をクリックしその回答を表示")
	void test06() {
		clickElement(getElementByCssSelector("dt[class='mb10']"));
		assertTrue(checkDisplayed(getElementById("answer-h[${status.index}]")));
		getEvidence(new Object(){});
	}

}
