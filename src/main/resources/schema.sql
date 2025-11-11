DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS book_names CASCADE;
DROP TABLE IF EXISTS books CASCADE;
DROP TABLE IF EXISTS lendings CASCADE;
DROP TABLE IF EXISTS genre CASCADE;

-- ユーザー
CREATE TABLE users(
    id INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,  -- ユーザーのID
    last_name VARCHAR(128) NOT NULL,   -- ユーザーの表示名
    first_name VARCHAR(128) NOT NULL,
    email VARCHAR(128) UNIQUE NOT NULL,  -- メールアドレス（ログイン時に利用）
    password VARCHAR(255) NOT NULL,  -- ハッシュ化済みのパスワード
    role INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    PRIMARY KEY(id)
);

-- 書籍名
CREATE TABLE book_names(
	id INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
	title VARCHAR(128) NOT NULL,
	author VARCHAR(128) NOT NULL,
	detail VARCHAR(1024) NOT NULL,
	publisher VARCHAR(128) NOT NULL,
	genre INTEGER NOT NULL,
	img VARCHAR(255),
	active INTEGER NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL,
	PRIMARY KEY(id)
	);

-- 書籍
CREATE TABLE books(
	id INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
	book_name_id INTEGER NOT NULL,
	active INTEGER NOT NULL,
	lendable INTEGER NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (book_name_id) REFERENCES book_names (id) ON DELETE CASCADE
);

-- 書籍貸出
CREATE TABLE lendings(
	id INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
	book_id INTEGER NOT NULL,
	user_id INTEGER NOT NULL,
	lend_date DATE NOT NULL,
	return_due_date DATE NOT NULL,
	return_date DATE,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (book_id) REFERENCES books (id),
	FOREIGN KEY (user_id) REFERENCES users (id)
);

-- ジャンル
CREATE TABLE genre(
	id INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
	name VARCHAR(128) NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL,
	PRIMARY KEY(id)
);
