const API_URL = 'http://localhost:8080/api/v1';

export const authService = {
  async login(username: string, password: string): Promise<string> {
    const response = await fetch(`${API_URL}/auth/login`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ username, password }),
    });

    if (!response.ok) {
      const errorData = await response.json().catch(() => ({}));
      throw new Error(errorData.message || 'Sai tài khoản hoặc mật khẩu.');
    }

    const data = await response.json();
    if (data.token) {
      if (typeof window !== 'undefined') {
        sessionStorage.setItem('token', data.token);
      }
      return data.token;
    }
    throw new Error('Không nhận được token xác thực từ máy chủ.');
  },

  logout(): void {
    if (typeof window !== 'undefined') {
      sessionStorage.removeItem('token');
    }
  },

  getToken(): string | null {
    if (typeof window !== 'undefined') {
      return sessionStorage.getItem('token');
    }
    return null;
  },

  isAuthenticated(): boolean {
    return !!this.getToken();
  },

  getUserRole(): string | null {
    const token = this.getToken();
    if (!token) return null;
    try {
      const payload = token.split('.')[1];
      const decodedPayload = atob(payload.replace(/-/g, '+').replace(/_/g, '/'));
      const parsed = JSON.parse(decodedPayload);
      return parsed.role || null;
    } catch (e) {
      console.error('Failed to decode token', e);
      return null;
    }
  }
};
