// @ts-check
import { defineConfig } from 'astro/config';
// @ts-ignore
import node from '@astrojs/node';
import tailwindcss from '@tailwindcss/vite';

export default defineConfig({
  output: 'server',
  adapter: node({
    mode: 'standalone'
  }),
  server: {
    host: '0.0.0.0',
    port: 4321,
  },
  vite: {
    plugins: [tailwindcss()],
    server: {
      proxy: {
        '/api': {
          target: 'http://api-gateway:8080',
          changeOrigin: true,
          configure: (proxy) => {
            proxy.on('proxyReq', (proxyReq) => {
              proxyReq.removeHeader('Origin');
            });
          }
        },
      },
    },
  },
});
