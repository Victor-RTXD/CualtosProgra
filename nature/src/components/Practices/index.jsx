import React from "react";

const practices = [
  {
    category: "Alimentación Sostenible",
    items: [
      {
        title: "Huertos verticales",
        description: "Cultiva en espacios reducidos con botellas recicladas",
        url: "#",
        image: "/images/huertos-verticales.jpg"
      },
      {
        title: "Composta comunitaria",
        description: "Organiza un sistema de compostaje vecinal",
        url: "#",
        image: "/images/composta-comunitaria.jpg"
      },
      // Agrega 3 más...
    ]
  },
  {
    category: "Reciclaje Creativo",
    items: [
      {
        title: "Muebles con palets",
        description: "Crea muebles únicos con palets reciclados",
        url: "#",
        image: "/images/muebles-palets.jpg"
      },
      {
        title: "Cortinas con tapas",
        description: "Elabora cortinas con tapas de botellas",
        url: "#",
        image: "/images/cortinas-tapas.jpg"
      },
      // Agrega 3 más...
    ]
  },
  {
    category: "Energía Limpia",
    items: [
      {
        title: "Calentador solar",
        description: "Construye un calentador de agua con botellas PET",
        url: "#",
        image: "/images/calentador-solar.jpg"
      },
      {
        title: "Cargador solar DIY",
        description: "Crea un cargador portátil con células fotovoltaicas",
        url: "#",
        image: "/images/cargador-solar.jpg"
      },
      // Agrega 3 más...
    ]
  },
  {
    category: "Agua y Conservación",
    items: [
      {
        title: "Sistema de captación pluvial",
        description: "Recolecta agua de lluvia con materiales reciclados",
        url: "#",
        image: "/images/captacion-pluvial.jpg"
      },
      {
        title: "Filtro de agua casero",
        description: "Construye un filtro con arena y grava",
        url: "#",
        image: "/images/filtro-agua.jpg"
      },
      // Agrega 3 más...
    ]
  },
  {
    category: "Tecnología Verde",
    items: [
      {
        title: "Baterías de papa",
        description: "Genera energía con tubérculos y zinc",
        url: "#",
        image: "/images/bateria-papa.jpg"
      },
      {
        title: "Robot con e-waste",
        description: "Reutiliza componentes electrónicos viejos",
        url: "#",
        image: "/images/robot-ewaste.jpg"
      },
      // Agrega 3 más...
    ]
  },
  // Continúa con más categorías...
];

function getCategoryColors(category) {
  const colors = {
    "Alimentación Sostenible": { bg: "bg-green-800/40", text: "text-green-300" },
    "Reciclaje Creativo": { bg: "bg-amber-700/40", text: "text-amber-300" },
    "Energía Limpia": { bg: "bg-yellow-600/40", text: "text-yellow-300" },
    "Agua y Conservación": { bg: "bg-blue-800/40", text: "text-blue-300" },
    "Tecnología Verde": { bg: "bg-emerald-800/40", text: "text-emerald-300" },
    "Moda Sostenible": { bg: "bg-fuchsia-800/40", text: "text-fuchsia-300" },
    "Educación Ambiental": { bg: "bg-purple-800/40", text: "text-purple-300" }
  };
  return colors[category] || { bg: "bg-slate-700/40", text: "text-gray-300" };
}

export default function Practices() {
  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-900 via-slate-800 to-slate-900 text-gray-100 px-4 py-12">
      <div className="container mx-auto">
        <header className="text-center mb-16">
          <h1 className="text-4xl md:text-5xl font-bold bg-gradient-to-r from-teal-400 to-emerald-400 bg-clip-text text-transparent mb-4">
            ODS 2030 - Buenas Prácticas
          </h1>
          <p className="text-lg text-gray-400 max-w-2xl mx-auto">
            Descubre 50 iniciativas eco-friendly para implementar en casa y contribuir 
            con los Objetivos de Desarrollo Sostenible de la ONU
          </p>
        </header>

        {practices.map((group, index) => {
          const { bg, text } = getCategoryColors(group.category);
          return (
            <section key={index} className="mb-16 scroll-mt-16" id={group.category.replace(/\s+/g, '-').toLowerCase()}>
              <h2 className={`text-3xl font-bold ${text} mb-8 flex items-center gap-3`}>
                <span className={`w-4 h-4 rounded-full ${bg.replace('/40', '')}`}></span>
                {group.category}
              </h2>
              <div className="grid md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
                {group.items.map((item, idx) => (
                  <article 
                    key={idx} 
                    className={`${bg} rounded-xl shadow-xl hover:shadow-2xl transition-shadow duration-300 hover:-translate-y-1 group overflow-hidden`}
                  >
                    <div className="relative h-48 overflow-hidden">
                      <img
                        src={item.image}
                        alt={item.title}
                        className="w-full h-full object-cover group-hover:scale-105 transition-transform duration-300"
                        loading="lazy"
                      />
                      <div className="absolute inset-0 bg-gradient-to-t from-slate-900/60 to-transparent"></div>
                    </div>
                    <div className="p-6">
                      <h3 className={`text-xl font-bold ${text} mb-3`}>
                        {item.title}
                      </h3>
                      <p className="text-gray-300 mb-4">{item.description}</p>
                      <a
                        href={item.url}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="inline-flex items-center gap-2 text-teal-400 hover:text-teal-300 font-medium transition-colors"
                      >
                        <span>Ver tutorial</span>
                        <svg className="w-4 h-4" fill="currentColor" viewBox="0 0 20 20">
                          <path fillRule="evenodd" d="M12.293 5.293a1 1 0 011.414 0l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414-1.414L14.586 11H3a1 1 0 110-2h11.586l-2.293-2.293a1 1 0 010-1.414z" clipRule="evenodd"/>
                        </svg>
                      </a>
                    </div>
                  </article>
                ))}
              </div>
            </section>
          );
        })}
      </div>
    </div>
  );
}