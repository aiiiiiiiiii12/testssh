using Microsoft.AspNetCore.Mvc;

namespace TestAzure.Controllers
{
    public class HTMLController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }


        public IActionResult Blog()
        {
            return View();
        }

        public IActionResult Contact()
        {
            return View();
        }
        public IActionResult About()
        {
            return View();
        }
        public IActionResult AppleTouchIcon()
        {
            return View("~/Views/HTML/apple-touch-icon.cshtml");
        }
        public IActionResult Cart()
        {
            return View();
        }
        public IActionResult LoginRegister()
        {
            return View("~/Views/HTML/login-register.cshtml");
        }
        public IActionResult ProductDetails()
        {
            return View("~/Views/HTML/product-details.cshtml");
        }
        public IActionResult Shop()
        {
            return View();
        }
        public IActionResult ShopSideBar()
        {
            return View("~/Views/HTML/shop-sidebar.cshtml");
        }
        public IActionResult Team()
        {
            return View();
        }
        public IActionResult WishList()
        {
            return View();
        }
        public IActionResult BlogDetails()
        {
            return View("~/Views/HTML/blog-details.cshtml");
        }
    }
}
