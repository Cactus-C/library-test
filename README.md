# librarymd

Generated with JHipster 8.11.0 and Claude.

## Development

Run backend and frontend in two separate terminals:

```bash
./mvnw
./npmw start
```

Install dependencies (only needed when `package.json` changes):

```bash
./npmw install
```

App runs at **http://localhost:8080**

---

## Docker

Start required services:

```bash
docker compose -f src/main/docker/services.yml up -d
```

Build and run the full app in Docker:

```bash
npm run java:docker
docker compose -f src/main/docker/app.yml up -d
```

---

## Testing with Postman

### Step 1 — Register a new user (Web UI)

The easiest way to create a librarian is to register directly from the app — this way the password is set immediately, no email or DB required.

1. Open **http://localhost:8080** in your browser
2. Click **Register** in the top right corner
3. Fill in the form with **username**, **email**, **password**.
4. Click **Register**

The account is created but is **not yet activated** and has only `ROLE_USER` by default.

---

### Step 2 — Login as Admin (Web UI)

1. Click **Sign in** in the top right corner
2. Enter the default admin credentials:

- Username: `admin`
- Password: `admin`

3. Click **Sign in**

---

### Step 3 — Activate the user and assign ROLE_LIBRARIAN (Web UI)

1. In the top navigation bar click **Administration**
2. Click **User Management**
3. Find `librarian` in the list and click **Edit**
4. In the edit form:

- **Activated:** check ✅ (the user is inactive by default after self-registration)
- **Profiles:** select `ROLE_LIBRARIAN` from the authorities list (remove `ROLE_USER` if needed)

5. Click **Save**

The user is now active and has the librarian role.

---

### Step 4 — Login as Librarian and get JWT token (Postman)

```
POST http://localhost:8080/api/authenticate
Content-Type: application/json
```

```json
{
  "username": "librarian",
  "password": "Librarian123!",
  "rememberMe": true
}
```

Save the `id_token` from the response as — you will use it in the next steps.

---

### Step 5 — Check available books

```
GET http://localhost:8080/api/books
```

No token required — this endpoint is public. Find a book with `copies > 0` and note its `id`.

---

### Step 6 — Borrow a book

```
POST http://localhost:8080/api/borrowed-books
Authorization: Bearer {{librarian_jwt_token}}
Content-Type: application/json
```

```json
{
  "book": { "id": 1 },
  "client": { "id": 1 }
}
```

Response will contain the `id` of the `BorrowedBook` record — save it for the next step.

---

### Step 7 — Return the book

```
DELETE http://localhost:8080/api/borrowed-books/5
Authorization: Bearer {{librarian_jwt_token}}
```

Response will contain 204 or 404 HTTP code.

---

## Useful Links

- [JHipster 8.11.0 docs](https://www.jhipster.tech/documentation-archive/v8.11.0)
- [Development guide](https://www.jhipster.tech/documentation-archive/v8.11.0/development/)
- [Production guide](https://www.jhipster.tech/documentation-archive/v8.11.0/production/)
