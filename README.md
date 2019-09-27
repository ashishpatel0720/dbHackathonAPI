#DBFrontrunnerAPI

this is an application for Developing API endpoints for FrontRunner Detection Application

  // For login
/users/login/                   {id : " " , name : " " , password : " "}

  // To get Orders of particular broker id
/users/orders/                  {brokerid : " "}

  // To execute orders (if executed the order is stored in data base else fired back)
/users/orders/execute/          {amount: " ", direction: " ", security:" ",brokerid:" ",quantity:" ",clientname:" ",isinno:" "}

  // To return the remaining amount that can be traded for given security in case of buy
/users/orders/buy/              {brokerid : " " , security : " "}

  // To return the remaining amount that can be traded for given security in case of sell
/users/orders/sell/             {brokerid : " " , security : " "}

  // To return all orders
/orders/                         GET METHOD

  // To verify whether the variance price is liable
/verify/variance/               { empid:" ", name_of_stock:" ", broker_price: }


//To get security from brokerid
/users/security/              { brokerid : " "}

//To get security from sectors
/users/sectors/securitiy


//To get sector limit and sector from userId
/users/limits/





