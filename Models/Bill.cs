﻿using Microsoft.AspNetCore.Identity;

namespace Project.Models
{
    public class Bill
    {
        public int BillId { get; set; }
        public int PaymentCode { get; set; }
        public string UserId { get; set; } = null!;
        public int TransportId { get; set; }
        public DateTime PurchaseDate { get; set; }
        public string BillStatus { get; set; } = null!;
        public string PaymentMethod { get; set; } = null!;
        public string ShippingAddress { get; set; } = null!;
        public string? Note { get; set; }
        public double ShippingFee { get; set; }

        public virtual Payment PaymentCodeNavigation { get; set; } = null!;
        public virtual Transport Transport { get; set; } = null!;
        public virtual IdentityUser User { get; set; } = null!;

        public virtual List<BillDetail> BillDetails { get; set; } = null!;
    }
}
