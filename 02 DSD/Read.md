# Tutorial Test 1 (DSD117V) — Noko Wholesalers

This solves Question 1 (39) and Question 2 (31) from `Tutorial_1-2026.pdf`, in the
same NetBeans "Enterprise Application" + GlassFish + Derby style as your Session 2/3
class examples (`FirstEnterpriseApp-ejb` / `FirstEnterpriseApp-war`).

## Project layout

```
NokoWholesalers-ejb/src/java/za/ac/tut/entity/   Person, Contact, Customer, Staff
NokoWholesalers-ejb/src/java/za/ac/tut/session/  PersonService(Bean), CounterService(Bean)
NokoWholesalers-ejb/src/conf/persistence.xml
NokoWholesalers-war/src/java/za/ac/tut/servlet/  PersonServlet
NokoWholesalers-war/web/                         customer.jsp, staff.jsp, result.jsp
```

If your own NetBeans project already exists with a different name/package, just
recreate these classes under your own EJB/WAR modules and paste the code in — NetBeans
will fix the `package` line automatically when you use File → New → Java Class in the
right folder, or you can find-and-replace `za.ac.tut` with your own package.

## Step 1 — Create the project in NetBeans

1. File → New Project → Java EE → **Enterprise Application**, name it `NokoWholesalers`,
   Server = GlassFish, Java EE version = whatever your course uses (7 in the examples).
   This creates the two modules `NokoWholesalers-ejb` and `NokoWholesalers-war` for you.
2. Copy the `.java` files into the matching packages under each module's `src/java`.
3. Copy `persistence.xml` into `NokoWholesalers-ejb` — right-click the ejb module →
   New → Other → Persistence → Persistence Unit is the easiest way to get NetBeans to
   create it in the right place (`src/conf/persistence.xml`), then just replace its
   contents with the one provided here (or use the wizard and pick "Data Source" =
   `jdbc/NokoDbase` in step 3 below, which produces the same file).
4. Copy the `.jsp` files into `NokoWholesalers-war/web`.

## Step 2 — Create NokoDbase in Derby + wire it up in GlassFish (Question 1.1)

This part is done in the GUI, not code, so here's the exact click-path:

1. In NetBeans, open the **Services** tab → Databases → right-click **Java DB** →
   **Create Database…**
   - Database name: `NokoDbase`
   - User name / password: pick something simple, e.g. `nbuser` / `nbuser`
   - This starts the Derby network server and creates the database.
2. Register the driver as a JDBC connection pool in GlassFish. Open the GlassFish
   Admin Console (usually `http://localhost:4848`) →
   **Resources → JDBC → JDBC Connection Pools → New**
   - Pool name: `NokoDbasePool`
   - Resource type: `javax.sql.DataSource`
   - Vendor: `Java DB (Derby)`
   - Next, then set the properties: `DatabaseName = NokoDbase`, `User = nbuser`,
     `Password = nbuser`, `PortNumber = 1527`, `ServerName = localhost`
   - Click **Finish**, then use **Ping** on the pool to confirm it connects.
3. **Resources → JDBC → JDBC Resources → New**
   - JNDI name: `jdbc/NokoDbase` (this must match `<jta-data-source>` in
     `persistence.xml`)
   - Pool name: `NokoDbasePool`

Once deployed, EclipseLink (`eclipselink.ddl-generation=create-tables` in
`persistence.xml`) will create `tblPerson`, `tblContact`, `tblCustomer` and `tblStaff`
in NokoDbase automatically the first time the app starts.

## How the pieces map to the tutorial

| Tutorial requirement | File |
|---|---|
| 1.2 persistence.xml | `NokoWholesalers-ejb/src/conf/persistence.xml` |
| 1.3 Person/Contact/Customer/Staff, JOINED strategy | `entity/Person.java`, `Contact.java`, `Customer.java`, `Staff.java` |
| 1.4 local stateless PersonServiceBean (add/get/getAll/update/delete) | `session/PersonService.java`, `PersonServiceBean.java` |
| 1.5 remote singleton CounterServiceBean | `session/CounterService.java`, `CounterServiceBean.java` |
| 2.1–2.3 PersonServlet.doPost (add / search / delete) | `servlet/PersonServlet.java` |
| 2.4 customer.jsp / staff.jsp + live client count | `web/customer.jsp`, `web/staff.jsp` |
| result.jsp table (Books) | `web/result.jsp` |

**One note on the brief:** Question 1.4.a says *"addPerson the method receives a
composite value object **Book**"* — that's almost certainly leftover wording from a
reused template (the class diagram itself clearly shows `addPerson(Person):void`), so
`addPerson` here takes a `Person`, matching the diagram and every other method
signature.

**Form field → entity field mapping**, since the diagram's field labels don't always
match the attribute names 1:1:

- Customer ID → `customerNo`, Staff Number → `staffNo` (these are business numbers,
  separate from the auto-generated `personID` primary key)
- "Contacts" in the form is just a section heading for cellphone / Telephone / email,
  which become one `Contact` row (`cellphoneNo`, `telephoneNo`, `email`)
- "floor Description" → `Staff.floor`

## Testing once deployed

1. Run the project (Run → Run Project). NetBeans opens the app's context root.
2. Go to `.../customer.jsp` — add a customer, note "Person Record is inserted" and the
   user count incrementing.
3. Go to `.../staff.jsp` — same for staff; the counter keeps climbing (shared
   singleton across both pages).
4. On either page, click **All** to list everyone in `result.jsp`, or **Search** with
   a name+surname to find one person.
5. Click **Delete** in the Action column of `result.jsp` — confirms "Person is
   Deleted".

## Submission checklist (per the tutorial's "Submit the following on EC")

Persistence.xml ✔ · Person.java ✔ · Contact.java ✔ · Customer.java ✔ · Staff.java ✔ ·
PersonService.java ✔ · PersonServiceBean.java ✔ · CounterService.java ✔ ·
CounterServiceBean.java ✔ · customer.jsp ✔ · staff.jsp ✔ · PersonServlet.java ✔ ·
result.jsp ✔
